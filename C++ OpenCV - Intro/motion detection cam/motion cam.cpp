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
    // Mat lastframe;
    Mat firstframe;
    Mat compare;
    VideoCapture cap;

    if (!cap.open(0))
        return 0;

    namedWindow("window", WINDOW_AUTOSIZE);

    cap >> firstframe;
    cvtColor(firstframe, firstframe, COLOR_RGB2GRAY);
    while (1)
    {
        if (GetKeyState(27) & 0x8000)
            break;
        // if (lastframe.empty())
        // {
        //     cap >> lastframe;
        //     cvtColor(lastframe, lastframe, COLOR_RGB2GRAY);
        // }
        cap >> image;
        cvtColor(image, image, COLOR_RGB2GRAY);
        // absdiff(image, lastframe, compare);
        absdiff(image, firstframe, compare);
        threshold(compare, compare, 60, 255, THRESH_BINARY);
        erode(compare, compare, Mat(), Point(-1, -1), 3);
        dilate(compare, compare, Mat());
        imshow("window", compare);
        // lastframe = image;
        waitKey(15);
    }

    return 0;
}