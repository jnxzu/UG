#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <conio.h>
#include <iostream>
#include <windows.h>
#include "TrackedMass.h"

#define MAX_OBJECTS 3

using namespace cv;
using namespace std;

bool compareCA(vector<Point> c1, vector<Point> c2)
{
    double i = fabs(contourArea(Mat(c1)));
    double j = fabs(contourArea(Mat(c2)));
    return (i > j);
}

int main()
{
    Mat image, hsv;
    VideoCapture cap;

    if (!cap.open(0))
        return 0;

    namedWindow("original", WINDOW_AUTOSIZE);
    namedWindow("hsv", WINDOW_AUTOSIZE);

    int slider_max = 255;
    int h_max_slider = 180;
    int h_min = 0, s_min = 0, v_min = 0, h_max = 180, s_max = 255, v_max = 255;

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
    vector<TrackedMass> trackedObjects;

    int mo;

    while (cap.read(image))
    {
        if (GetKeyState(27) & 0x8000)
            break;
        cvtColor(image, hsv, COLOR_RGB2HSV);
        erode(hsv, hsv, Mat(), Point(0, 0), 6);
        dilate(hsv, hsv, Mat(), Point(0, 0), 6);
        inRange(hsv, Scalar(h_min, s_min, v_min), Scalar(h_max, s_max, v_max), hsv);

        findContours(hsv, contours, RETR_EXTERNAL, CHAIN_APPROX_NONE);
        sort(contours.begin(), contours.end(), compareCA);
        mo = MAX_OBJECTS;
        if (contours.size() < mo)
            mo = contours.size();

        for (int i = 0; i < mo; i++)
        {
            TrackedMass tm(contours[i]);
            tm.setCenter();
            trackedObjects.push_back(tm);
        }
        contours.clear();
        for (TrackedMass tm : trackedObjects)
        {
            Point center = tm.getCenter();
            string str = to_string(center.x).append(".").append(to_string(center.y));
            putText(image, str, center, FONT_HERSHEY_SIMPLEX, 0.5, (100, 100, 100));
            contours.push_back(tm.getContour());
        }
        drawContours(image, contours, -1, (100, 100, 100));
        imshow("hsv", hsv);
        imshow("original", image);
        trackedObjects.clear();
        waitKey(15);
    }

    return 0;
}