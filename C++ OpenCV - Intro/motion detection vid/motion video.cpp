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
    Mat lastframe;
    Mat compare;
    VideoCapture vid("robot.avi");

    namedWindow("src", WINDOW_AUTOSIZE);
    namedWindow("movement", WINDOW_AUTOSIZE);

    while (1)
    {
        if ((GetKeyState(27) & 0x8000))
            break;
        if (lastframe.empty())
        {
            vid >> lastframe;
            cvtColor(lastframe, lastframe, COLOR_RGB2GRAY);
        }
        vid >> image;
        if (image.empty())
            break;
        cvtColor(image, image, COLOR_RGB2GRAY);
        absdiff(image, lastframe, compare);
        threshold(compare, compare, 60, 255, THRESH_BINARY);
        // erode(compare, compare, Mat(), Point(-1, -1), 3);
        // dilate(compare, compare, Mat());
        imshow("src", image);
        imshow("movement", compare);
        lastframe = image;
        waitKey(15);
    }

    return 0;
}