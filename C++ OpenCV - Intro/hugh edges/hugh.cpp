#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <conio.h>
#include <iostream>
#include <windows.h>

using namespace cv;
using namespace std;

int main()
{
    VideoCapture cam(0);
    Mat src, edges;

    namedWindow("config", 1);

    int len = 50, thresh = 50, gap = 10;

    createTrackbar("len", "config", &len, 100);
    createTrackbar("gap", "config", &gap, 100);
    createTrackbar("thresh", "config", &thresh, 100);

    while (1)
    {
        if (GetKeyState(27) & 0x8000)
            break;
        cam >> src;

        Canny(src, edges, 50, 200, 3);
        vector<Vec4i> lines;
        HoughLinesP(edges, lines, 1, CV_PI / 180, thresh, len, gap);

        for (int i = 0; i < lines.size(); i++)
        {
            Vec4i l = lines[i];
            line(src, Point(l[0], l[1]), Point(l[2], l[3]), Scalar(0, 255, 0), 3);
        }

        imshow("", src);

        waitKey(15);
    }

    destroyAllWindows();
    src.release();

    return 0;
}