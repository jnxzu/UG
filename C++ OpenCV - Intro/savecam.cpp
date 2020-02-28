#include <opencv2/highgui.hpp>
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
    Mat img(1280, 720, CV_8UC3, Scalar(255, 0, 128));
    vector<int> compression_params;
    compression_params.push_back(IMWRITE_JPEG_QUALITY);
    compression_params.push_back(100);

    int frames;
    int wait;
    cout << "Frames: ";
    cin >> frames;
    cout << "Wait(ms): ";
    cin >> wait;

    clock_t start = clock();
    while (clock() - start < wait)
    {
        cap >> img;
        imshow("window", img);
        waitKey(15);
    }

    for (int i = 0; i < frames; i++)
    {
        cap >> img;
        String filename = "camframes/frame" + to_string(i) + ".jpg";
        imwrite(filename, img, compression_params);
        waitKey(15);
    }

    return 0;
}