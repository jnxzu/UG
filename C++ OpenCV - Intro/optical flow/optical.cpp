#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/video.hpp>

#include "windows.h"
#include <queue>
#include <iostream>

using namespace cv;
using namespace std;

struct comp
{
    bool operator()(Point2f a, Point2f b)
    {
        return a.y < b.y;
    }
} obj;

int main()
{
    VideoCapture in;
    Mat frameA, frameB, motion, output, err;

    int corners = 20;
    int quality = 20;
    int distance = 10;

    int choice = 0;

    vector<Point2f> featuresThis, featuresNext;
    vector<uchar> status;
    deque<vector<Point2f>> path;

    cout << "1. CAM\n2. VID" << endl;

    cin >> choice;

    switch (choice)
    {
    case 1:
        in.open(0);
        break;

    case 2:
        in.open("bike.mp4");
        break;

    default:
        cout << "..." << endl;
        return -1;
    }

    namedWindow("config", 1);

    createTrackbar("corners", "config", &corners, 1000);
    createTrackbar("qual", "config", &quality, 100);
    createTrackbar("dist", "config", &distance, 25);

    int thickness;
    int checker = 0;
    while (true)
    {
        if (GetKeyState(27) & 0x8000)
            break;

        in >> frameA;
        if (frameA.empty()) // video loop
        {
            in.set(CAP_PROP_POS_FRAMES, 0);
            in >> frameA;
            checker = 0;
        }
        output = frameA;
        cvtColor(frameA, frameA, COLOR_RGB2GRAY);

        if (!frameB.empty())
        {
            absdiff(frameA, frameB, motion);
            threshold(motion, motion, 50, 150, 0);
            if (checker % 5 == 0)
            {
                if (quality == 0)
                    quality = 1;
                goodFeaturesToTrack(motion, featuresThis, corners, (double)quality / 100, distance);
            }
            if (!featuresThis.empty())
            {
                calcOpticalFlowPyrLK(frameB, frameA, featuresThis, featuresNext, status, err);
                sort(featuresNext.begin(), featuresNext.end(), obj);
                path.push_front(featuresNext);
                if (path.size() == 10)
                    path.pop_back();
                for (size_t i = 0; i < path.size() - 1; i++)
                    for (size_t j = 0; j < path[i].size(); j++)
                    {
                        if (i == 0)
                            circle(output, path[i][j], 3, (0, 255, 0), -1);
                        else
                            line(output, path[i][j], path[i + 1][j], (255, 255, 255));
                    }
                featuresThis = featuresNext;
            }
        }

        imshow("test", output);
        frameB = frameA;
        checker++;
        waitKey(15);
    }

    destroyAllWindows();

    frameA.release();
    frameB.release();
    output.release();
    in.release();

    return 0;
}