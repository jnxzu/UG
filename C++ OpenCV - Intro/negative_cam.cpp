#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <conio.h>
#include <iostream>
#include <windows.h>

using namespace cv;
using namespace std;

int main()
{
    Mat image;
    Mat edited;
    VideoCapture cap;

    if (!cap.open(0))
        return 0;

    namedWindow("window", WINDOW_AUTOSIZE);

    while (1)
    {
        if (GetKeyState(27) & 0x8000)
            break;
        cap >> image;
        cvtColor(image, edited, COLOR_RGB2GRAY);
        bitwise_not(edited, edited);
        imshow("window", edited);
        imshow("window2", image);
        waitKey(15);
    }

    return 0;
}