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
    Mat hsv;
    Mat edited;
    VideoCapture cap;

    if (!cap.open(0))
        return 0;

    namedWindow("original", WINDOW_AUTOSIZE);
    namedWindow("hsv", WINDOW_AUTOSIZE);

    int slider_max = 255;
    int h_max_slider = 180;
    int h_min, s_min, v_min, h_max, s_max, v_max = 0;

    char hMaxName[50];
    sprintf(hMaxName, "H max %d", h_max_slider);
    createTrackbar(hMaxName, "hsv", &h_max, h_max_slider);
    char hMinName[50];
    sprintf(hMinName, "H min %d", h_max_slider);
    createTrackbar(hMinName, "hsv", &h_min, h_max_slider);
    char sMaxName[50];
    sprintf(sMaxName, "S max %d", slider_max);
    createTrackbar(sMaxName, "hsv", &s_max, slider_max);
    char sMinName[50];
    sprintf(sMinName, "S min %d", slider_max);
    createTrackbar(sMinName, "hsv", &s_min, slider_max);
    char vMaxName[50];
    sprintf(vMaxName, "V max %d", slider_max);
    createTrackbar(vMaxName, "hsv", &v_max, slider_max);
    char vMinName[50];
    sprintf(vMinName, "V min %d", slider_max);
    createTrackbar(vMinName, "hsv", &v_min, slider_max);

    vector<vector<Point>> contours;
    Moments moment;
    Point massCenter;

    while (1)
    {
        if (GetKeyState(27) & 0x8000)
            break;
        cap >> image;
        cvtColor(image, hsv, COLOR_RGB2HSV);
        erode(hsv, hsv, Mat(), Point(0, 0), 2);
        dilate(hsv, hsv, Mat(), Point(0, 0), 2);
        inRange(hsv, Scalar(h_min, s_min, v_min), Scalar(h_max, s_max, v_max), hsv);
        try
        {
            findContours(hsv, contours, 2, 2);
            if (contours.size() == 1)
            {
                moment = moments(contours[0]);
                massCenter = Point2f(moment.m10 / moment.m00, moment.m01 / moment.m00);
                putText(image, "TRACKING", massCenter, FONT_HERSHEY_SIMPLEX, 2, (0, 0, 0));
            }
        }
        catch (Exception e)
        {
        }

        imshow("original", image);
        imshow("hsv", hsv);
        waitKey(15);
    }

    return 0;
}