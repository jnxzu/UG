#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <conio.h>
#include <iostream>
#include <windows.h>

using namespace cv;
using namespace std;

int main()
{
    Mat image, gX, gY, gXY, canny, orientation, colors;
    VideoCapture cap;

    if (!cap.open(0))
        return 0;

    namedWindow("config", WINDOW_NORMAL);
    resizeWindow("config", Size(500, 200));

    int gaussSize = 0;
    createTrackbar("Gauss", "config", &gaussSize, 24);
    int thLow = 50;
    createTrackbar("low thresh", "config", &thLow, 100);
    int thHigh = 255;
    createTrackbar("high thresh", "config", &thHigh, 255);
    int sobelSize = 0;
    createTrackbar("sobel size", "config", &sobelSize, 3);

    while (1)
    {
        if (GetKeyState(27) & 0x8000)
            break;
        cap >> image;
        cvtColor(image, image, COLOR_RGB2GRAY);

        // GAUSSIAN BLUR
        if (gaussSize == 0 || gaussSize % 2 == 0)
            GaussianBlur(image, image, Size(gaussSize + 1, gaussSize + 1), 0);
        else
            GaussianBlur(image, image, Size(gaussSize, gaussSize), 0);
        imshow("gauss", image);

        // X EDGES
        if (sobelSize % 2 == 0 || sobelSize == 0)
            Sobel(image, gX, CV_32F, 1, 0, sobelSize * 2 + 1);
        else
            Sobel(image, gX, CV_32F, 1, 0, sobelSize);
        convertScaleAbs(gX, gX);
        imshow("gX", gX);

        // Y EDGES
        if (sobelSize % 2 == 0 || sobelSize == 0)
            Sobel(image, gY, CV_32F, 0, 1, sobelSize * 2 + 1);
        else
            Sobel(image, gY, CV_32F, 0, 1, sobelSize);
        convertScaleAbs(gY, gY);
        imshow("gY", gY);

        // ABOVE COMBINED
        addWeighted(gX, 0.5, gY, 0.5, 0, gXY);
        threshold(gXY, gXY, thLow, thHigh, 0);
        imshow("gXY", gXY);

        // CANNY
        Canny(image, canny, 50, 150);
        imshow("canny", canny);

        // ANGLE COLORS
        gX.convertTo(gX, CV_32F);
        gY.convertTo(gY, CV_32F);
        phase(gX, gY, orientation, true);
        normalize(orientation, orientation, 0.0, 255.0, NORM_MINMAX, CV_8U);
        orientation.copyTo(colors);
        cvtColor(colors, colors, COLOR_GRAY2RGB);

        for (int i = 0; i < orientation.rows; i++)
        {
            for (int j = 0; j < orientation.cols; j++)
            {
                int angle = orientation.at<uchar>(i, j);
                Vec3b edge = canny.at<uchar>(i, j);
                if (edge[0] == edge[1] == edge[2] == 0)
                {
                    colors.at<Vec3b>(i, j)[0] = 0;
                    colors.at<Vec3b>(i, j)[1] = 0;
                    colors.at<Vec3b>(i, j)[2] = 0;
                    continue;
                }

                if (angle > 45 && angle <= 135)
                {
                    colors.at<Vec3b>(i, j)[0] = 255;
                    colors.at<Vec3b>(i, j)[1] = 255;
                    colors.at<Vec3b>(i, j)[2] = 255;
                }
                else if (angle > 135 && angle <= 225)
                {
                    colors.at<Vec3b>(i, j)[0] = 255;
                    colors.at<Vec3b>(i, j)[1] = 0;
                    colors.at<Vec3b>(i, j)[2] = 0;
                }
                else if (angle > 225 && angle <= 315)
                {
                    colors.at<Vec3b>(i, j)[0] = 0;
                    colors.at<Vec3b>(i, j)[1] = 255;
                    colors.at<Vec3b>(i, j)[2] = 0;
                }
                else if (angle > 315 || (angle > 0 && angle <= 45))
                {
                    colors.at<Vec3b>(i, j)[0] = 0;
                    colors.at<Vec3b>(i, j)[1] = 0;
                    colors.at<Vec3b>(i, j)[2] = 255;
                }
                else
                {
                    colors.at<Vec3b>(i, j)[0] = 0;
                    colors.at<Vec3b>(i, j)[1] = 0;
                    colors.at<Vec3b>(i, j)[2] = 0;
                }
            }
        }

        imshow("colors", colors);

        waitKey(15);
    }
    destroyAllWindows();
    image.release();
    return 0;
}