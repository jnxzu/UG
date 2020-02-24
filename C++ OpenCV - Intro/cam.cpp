#include <opencv2/highgui.hpp>
#include <conio.h>
#include <iostream>
#include <windows.h>

using namespace cv;
using namespace std;

int main()
{
    Mat image;
    VideoCapture cap;

    if (!cap.open(0))
        return 0;

    namedWindow("window", WINDOW_AUTOSIZE);
    double dWidth = cap.set(CAP_PROP_FRAME_WIDTH, 640);
    double dHeight = cap.set(CAP_PROP_FRAME_HEIGHT, 480);

    while (1)
    {
        if (GetKeyState(27) & 0x8000)
            break;
        cap >> image;
        imshow("window", image);
        waitKey(15);
    }

    return 0;
}