#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>

#include <conio.h>
#include <windows.h>
#include <iostream>

using namespace std;
using namespace cv;

VideoCapture src;
Mat frame1, frame2, diff, blurdiff, thresh, draw;
Rect myRect;
vector<vector<Point>> contours, hull(1), poly(1);
Moments moment;
Point massCenter;

int mode = 0, key = 0;
string str;

int main()
{
    src.open("bike.mp4");

    src.read(frame1);
    src.read(frame2);
    cvtColor(frame1, frame1, COLOR_RGB2GRAY);

    while (true)
    {
        frame2.copyTo(draw);
        cvtColor(frame2, frame2, COLOR_RGB2GRAY);
        if (mode != 0)
        {
            absdiff(frame1, frame2, diff);
            GaussianBlur(diff, blurdiff, Size(5, 5), 0);
            threshold(blurdiff, thresh, 20, 255, THRESH_BINARY);
            findContours(thresh, contours, RETR_EXTERNAL, CHAIN_APPROX_SIMPLE);

            if (contours.size() > 0)
            {
                int bestContour = 0;
                for (size_t i = 0; i < contours.size(); i++)
                    if (contourArea(contours[i]) > contourArea(contours[bestContour]))
                        bestContour = i;

                moment = moments(contours[bestContour]);
                massCenter = Point2f(moment.m10 / moment.m00, moment.m01 / moment.m00);
                str = to_string(massCenter.x).append(".").append(to_string(massCenter.y));
                putText(draw, str, massCenter, FONT_HERSHEY_SIMPLEX, 0.5, (0, 0, 0), 2);

                if (mode == 1)
                {
                    myRect = boundingRect(contours[bestContour]);

                    rectangle(draw, myRect, (255, 255, 255));
                }
                if (mode == 2)
                {
                    convexHull(contours[bestContour], hull[0]);

                    drawContours(draw, hull, -1, (255, 255, 255));
                }
                if (mode == 3)
                {
                    approxPolyDP(contours[bestContour], poly[0], 3, true);

                    drawContours(draw, poly, -1, (255, 255, 255));
                }
            }
        }

        imshow("Display", draw);

        frame1 = frame2.clone();
        src >> frame2;
        if (frame2.empty()) // video loop
        {
            src.set(CAP_PROP_POS_FRAMES, 0);
            src >> frame2;
        }

        key = waitKey(45);

        if (key == 109)
            mode = (mode + 1) % 4;

        if (key == 27)
            break;
    }

    frame1.release();
    frame2.release();
    diff.release();
    blurdiff.release();
    thresh.release();
    draw.release();

    destroyAllWindows();
}
