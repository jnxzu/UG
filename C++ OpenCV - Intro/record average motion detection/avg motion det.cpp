#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/opencv.hpp>
#include <conio.h>
#include <iostream>
#include <windows.h>

using namespace cv;
using namespace std;

#define THRESHOLD 50

int main()
{
    Mat image, firstframe, diff, accum, save;
    // VideoCapture cap;
    VideoCapture cap("robot.avi");

    double alpha = 0.09;

    // if (!cap.open(0))
    //     return 0;

    namedWindow("src", WINDOW_AUTOSIZE);
    namedWindow("accumDiff", WINDOW_AUTOSIZE);

    cap >> firstframe;
    cvtColor(firstframe, firstframe, COLOR_RGB2GRAY);
    accum = firstframe;

    int codec = VideoWriter::fourcc('M', 'J', 'P', 'G');
    VideoWriter writer("out.avi", codec, 20, accum.size(), true);

    while (1)
    {
        if (GetKeyState(27) & 0x8000)
            break;
        cap >> image;
        if (image.empty())
            break;
        cvtColor(image, image, COLOR_RGB2GRAY);
        absdiff(image, firstframe, diff);
        threshold(diff, diff, THRESHOLD, 255, THRESH_BINARY);
        accum = (1 - alpha) * accum + alpha * diff;
        imshow("src", image);
        imshow("accumDiff", accum);
        // cvtColor(accum, save, COLOR_GRAY2RGB);
        writer.write(save);
        waitKey(15);
    }

    return 0;
}