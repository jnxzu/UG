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
    Mat frameA, frameB, motion, output, err, usedframe;

    int corners = 20;
    int quality = 20;
    int distance = 10;

    int choice = 2;

    vector<Point2f> featuresThis, featuresNext;
    vector<uchar> status;

    // cout << "1. CAM\n2. VID" << endl;

    // cin >> choice;

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
        output = frameA.clone();
        cvtColor(frameA, frameA, COLOR_RGB2GRAY);

        if (!frameB.empty())
        {
            if (checker % 5 == 0)
            {
                usedframe = frameB.clone();
                absdiff(frameA, frameB, motion);
                threshold(motion, motion, 50, 150, 0);
                if (quality == 0)
                    quality = 1;
                goodFeaturesToTrack(motion, featuresThis, corners, (double)quality / 100, distance);
            }
            if (!featuresThis.empty())
            {
                calcOpticalFlowPyrLK(usedframe, frameA, featuresThis, featuresNext, status, err);
                sort(featuresNext.begin(), featuresNext.end(), obj);
                vector<Point2f> good;
                cvtColor(output, output, COLOR_RGBA2RGB);
                for (uint i = 0; i < featuresThis.size(); i++)
                {
                    if (status[i] == 1)
                    {
                        good.push_back(featuresNext[i]);
                        line(output, featuresNext[i], featuresThis[i], (0, 255, 0), 3);
                        circle(output, featuresNext[i], 3, (255, 0, 0), -1);
                    }
                }
            }
        }

        imshow("test", output);
        frameB = frameA.clone();
        checker++;
        waitKey(30);
    }

    destroyAllWindows();

    frameA.release();
    frameB.release();
    output.release();
    in.release();

    return 0;
}