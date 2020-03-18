#include <opencv2/highgui.hpp>
#include <opencv2/opencv.hpp>
#include <conio.h>
#include <iostream>
#include <windows.h>

#define FRAMES 15

using namespace cv;
using namespace std;

int main()
{
    VideoCapture cap;

    if (!cap.open(0))
        return -1;

    namedWindow("window", WINDOW_AUTOSIZE);
    Size framesize(640, 480);
    int codec = VideoWriter::fourcc('M', 'J', 'P', 'G');
    VideoWriter writer("out.avi", codec, 20, framesize, true);
    while (1)
    {
        if (GetKeyState(27) & 0x8000)
            break;
        Mat frame;
        cap >> frame;
        imshow("window", frame);
        writer.write(frame);
        waitKey(15);
    }

    return 0;
}