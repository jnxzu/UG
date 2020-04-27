#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/video.hpp>

#include "windows.h"

#include <iostream>

using namespace cv;
using namespace std;

int main()
{
    VideoCapture in;
    Mat src, movement, bg;

    int hist = 100;
    int nmix = 1;
    int shadows = 0;

    // CAM
    // in.open(0);
    // VID
    in.open("bike.mp4");

    namedWindow("config", 1);

    createTrackbar("hist", "config", &hist, 500);
    createTrackbar("nmix", "config", &nmix, 5);
    createTrackbar("shadow", "config", &shadows, 1);

    Ptr<BackgroundSubtractorMOG2> mog = createBackgroundSubtractorMOG2();

    while (true)
    {
        if (GetKeyState(27) & 0x8000)
            break;
        in >> src;

        if (src.empty()) // video loop
        {
            in.set(CAP_PROP_POS_FRAMES, 0);
            in >> src;
        }

        mog->apply(src, movement);
        mog->setHistory(hist);
        mog->setNMixtures(nmix);
        mog->setDetectShadows(static_cast<bool>(shadows));
        mog->getBackgroundImage(bg);

        medianBlur(movement, movement, 5); // looks better
        vector<vector<Point>> contours;
        findContours(movement, contours, RETR_LIST, CHAIN_APPROX_SIMPLE);

        for (int i = 0; i < contours.size(); i++)
            drawContours(src, contours, i, Scalar(0, 0, 255), 2);

        imshow("movement", src);
        imshow("bg", bg);

        waitKey(15);
    }

    destroyAllWindows();

    in.release();
    src.release();
    bg.release();
    movement.release();

    return 0;
}