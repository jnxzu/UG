#include <opencv2/highgui.hpp>
#include <opencv2/video.hpp>

#include <deque>
#include <string>

using namespace cv;

VideoCapture src;          // input source
Mat frame, motion, draw;   // input, motion, output matrices
std::deque<int> zoneDeque; // deque for zone detections

Ptr<BackgroundSubtractorMOG2> mog = createBackgroundSubtractorMOG2(); // background substractor

int zoneCount = 0;         // pedestrians found in the detection zone in particular frame
int countHist = 5;         // amount of frames to use for average calculation
int zoneSum = 0;           // sum of deque values for average calculation
int zoneAvg = 0;           // average of pedestrains detected in the zone over set amount of frames
int lastAvg = 0;           // previous average
int hist = 50;             // history parameter for background substractor
int blurSize = 3;          // kernel size for blur function
int minSize = 500;         // minimum size for contour detection
int input = 0;             // input key
int frameCounter = 0;      // frame counter for increasing pedestrian total
int thresh = 60;           // threshold parameter for threshold function
int pointX = 0;            // x of mouse click location
int pointY = 0;            // y of mouse click location
int captureZoneWidth = 50; // size of capturing zone
int pedestrianCounter = 0; // total pedestrians captures so far
bool configFlag = true;    // wheter config window should be opened
bool flipFlag = false;     // capture zone orientation (false = vertical, true = horizontal)

/* helper function for drawing UI 
    input/output image
    capture zone orientation
    x of capture zone center
    y of capture zone center
    zone width
    current amount of pedestrians in the zone
    total amount of pedestrians detected in the zone
*/
Mat drawUI(Mat frame, bool flip, int x, int y, int w, int curNum, int totalNum)
{
    // draw capture zone borders
    if (flip)
    {
        line(draw, Point(0, y - w / 2), Point(frame.cols, y - w / 2), Scalar(255, 0, 0), 2);
        line(draw, Point(0, y + w / 2), Point(frame.cols, y + w / 2), Scalar(255, 0, 0), 2);
    }
    else
    {
        line(draw, Point(x - w / 2, 0), Point(x - w / 2, frame.rows), Scalar(255, 0, 0), 2);
        line(draw, Point(x + w / 2, 0), Point(x + w / 2, frame.rows), Scalar(255, 0, 0), 2);
    }

    // draw text displaying capture numbers
    String currently = "currently in zone: ";
    String total = "total: ";
    rectangle(frame, Rect(Point(0, 0), Point(200, 50)), Scalar(255, 255, 255), -1);
    putText(frame, currently.append(std::to_string(curNum)), Point(0, 15), 0, 0.5, Scalar(0, 0, 0), 2);
    putText(frame, total.append(std::to_string(totalNum)), Point(0, 35), 0, 0.5, Scalar(0, 0, 0), 2);

    return frame;
}

/* motion analysis function
    input image
    kernel size for blur function
    threshold parameter for threshold function
*/
std::vector<std::vector<Point>> analyzeMotion(Mat frame, int blurVal, int threshVal)
{
    Mat motion;
    std::vector<std::vector<Point>> contours;

    // detect motion
    mog->setHistory(hist);
    mog->setNMixtures(7); // hardcoded best value for my computer
    mog->apply(frame, motion);

    blur(motion, motion, Size(2 * blurVal + 1, 2 * blurVal + 1)); // kernel size must be an odd number
    threshold(motion, motion, threshVal, 255, THRESH_BINARY);

    // generate config window
    if (configFlag)
    {
        createTrackbar("hist", "motion", &hist, 250);
        createTrackbar("blurSize", "motion", &blurSize, 12);
        createTrackbar("minSize", "motion", &minSize, 1000);
        createTrackbar("thresh", "motion", &thresh, 150);
        createTrackbar("countHist", "motion", &countHist, 20);
        createTrackbar("zoneWidth", "motion", &captureZoneWidth, 200);
        imshow("motion", motion);
    }

    findContours(motion, contours, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE); // find only outermost contours in hierarchy

    return contours;
}

/* function capturing a mouse click
    mouse event
    cursor location x
    cursor location y
*/
void onMouse(int event, int x, int y, int flags, void *)
{
    if (event != EVENT_LBUTTONDOWN) // ignore any other mouse action that LMB click
        return;

    pointX = x;
    pointY = y;
}

int main()
{
    // try to open source file
    // fail if source file cannot be opened
    try
    {
        src.open("vtest.avi");
    }
    catch (const std::exception e)
    {
        return -1;
    }

    namedWindow("motion", 1);
    namedWindow("display", 1);

    setMouseCallback("display", onMouse);

    while (true)
    {
        src.read(frame);

        // video loop
        if (frame.empty())
        {
            src.set(CAP_PROP_POS_FRAMES, 0);
            src.read(frame);
        }

        // default values for capture zone positioning
        if (pointX == 0)
            pointX = frame.cols / 2;
        if (pointY == 0)
            pointY = frame.rows / 2;

        // copy frame for further drawing
        frame.copyTo(draw);

        // find contours of all pedestrians
        std::vector<std::vector<Point>> contours = analyzeMotion(frame, blurSize, thresh);

        // draw bounding rectangles around found contours
        // color accordingly to position (green in capture zone/red outside)
        // increment the amount of pedestrians found in the zone
        zoneCount = 0;
        for (size_t i = 0; i < contours.size(); i++)
        {
            if (contourArea(contours[i]) < minSize)
                continue;
            Scalar color;
            Rect rect;
            Moments moment = moments(contours[i]);
            Point2f massCenter = Point2f(moment.m10 / moment.m00, moment.m01 / moment.m00);
            if (flipFlag)
                if (massCenter.y > pointY - captureZoneWidth / 2 && massCenter.y < pointY + captureZoneWidth / 2)
                {
                    color = Scalar(0, 255, 0);
                    zoneCount++;
                }
                else
                {
                    color = Scalar(0, 0, 255);
                }
            else if (massCenter.x > pointX - captureZoneWidth / 2 && massCenter.x < pointX + captureZoneWidth / 2)
            {
                color = Scalar(0, 255, 0);
                zoneCount++;
            }
            else
            {
                color = Scalar(0, 0, 255);
            }
            rect = boundingRect(contours[i]);
            rectangle(draw, rect, color, 3);
        }

        zoneDeque.push_front(zoneCount); // push the amount of detected pedestrians to deque

        if (zoneDeque.size() > countHist + 1)       // edge case fix of lowering countHist param
            zoneDeque.pop_back();                   // adjust deque size
        else if (zoneDeque.size() == countHist + 1) // proceed if deque size correct
        {
            // calculate the average amount of detected pedestrians over the specified number of frames
            zoneSum = 0;
            for (size_t i = 0; i < countHist; i++)
            {
                zoneSum += zoneDeque.at(i);
            }
            zoneAvg = zoneSum / countHist;

            zoneDeque.pop_back(); // adjust deque size

            if (frameCounter == 0)
            {
                if (zoneAvg > lastAvg && !configFlag)            // only if not currently configuring
                    pedestrianCounter += abs(zoneAvg - lastAvg); // increase the pedestrian total
                lastAvg = zoneAvg;                               // move zone average to last zone average
            }
        }

        // draw information
        draw = drawUI(draw, flipFlag, pointX, pointY, captureZoneWidth, zoneAvg, pedestrianCounter);

        // display final image
        imshow("display", draw);

        if (!countHist) // countHist cannot be size 0 because of 0 division
            countHist = 1;

        // increment or reset frame counter
        frameCounter = (frameCounter + 1) % countHist;

        // user input handling
        input = waitKey(45);
        if (input == 27)
            break;
        if (input == 99)
            if (!configFlag)
                configFlag = true;
            else
            {
                configFlag = false;
                destroyWindow("motion");
            }
        if (input == 102)
            if (flipFlag)
                flipFlag = false;
            else
                flipFlag = true;
    }
    destroyAllWindows();
}
