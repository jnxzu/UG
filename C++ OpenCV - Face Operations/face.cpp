#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>
#include <opencv2/objdetect.hpp>

#include <iostream>
#include <math.h>

using namespace cv;
using namespace std;

int main()
{
    VideoCapture src(0);
    Mat original, gray, draw;
    CascadeClassifier faceClass, eyesClass;
    vector<Rect> faces, eyes;
    vector<Point> facePoints;
    Point2f faceCenter;

    faceClass.load("haar_face.xml");
    eyesClass.load("haar_eyes.xml");

    namedWindow("config", 1);

    int scale = 10, neighbors = 3, size = 30, key = 0;
    bool blurFlag = false, censorFlag = false, swapFlag = false, circleFlag = false;
    float radius = 0;

    createTrackbar("scale", "config", &scale, 100);
    createTrackbar("neighbors", "config", &neighbors, 10);
    createTrackbar("min. size", "config", &size, 50);

    while (true)
    {
        src >> original;

        original.copyTo(draw);

        cvtColor(original, gray, COLOR_BGR2GRAY);
        equalizeHist(gray, gray);

        double dScale = 1.0 + (double)scale / 100;
        if (dScale < 1.05)
            dScale = 1.05;

        faceClass.detectMultiScale(gray, faces, dScale, neighbors, 0, Size(size, size));

        Rect mainFace = faces[0];
        facePoints.push_back(mainFace.tl());
        facePoints.push_back(mainFace.br());
        minEnclosingCircle(facePoints, faceCenter, radius);
        Mat faceMat = gray(faces[0]);

        eyesClass.detectMultiScale(faceMat, eyes, 1.1, 3, 0, Size(30, 30));

        Point leftSide(mainFace.x, mainFace.y + mainFace.height / 2);
        Rect eyesArea(leftSide - Point(0, mainFace.height / 3), Size(mainFace.width, mainFace.height / 3));

        if (key == 98) // (B)LUR
            if (blurFlag)
                blurFlag = false;
            else
                blurFlag = true;

        if (key == 101) // (E)YES
            if (censorFlag)
                censorFlag = false;
            else
                censorFlag = true;

        if (key == 115) // (S)WAP
            if (swapFlag)
                swapFlag = false;
            else
                swapFlag = true;

        if (key == 99) // (C)IRCLE
            if (circleFlag)
                circleFlag = false;
            else
                circleFlag = true;

        if (blurFlag)
        {
            GaussianBlur(draw(mainFace), draw(mainFace), Size(33, 33), 20);
            putText(draw, "(B)lur - Active", Point(0, 25), FONT_HERSHEY_SIMPLEX, 0.75, (0, 0, 0), 3);
        }
        else
            putText(draw, "(B)lur", Point(0, 25), FONT_HERSHEY_SIMPLEX, 0.75, (0, 0, 0), 3);

        if (censorFlag)
        {
            rectangle(draw, eyesArea, Scalar(0, 0, 0), -1);
            putText(draw, "Hide (E)yes - Active", Point(0, 50), FONT_HERSHEY_SIMPLEX, 0.75, (0, 0, 0), 3);
        }
        else
            putText(draw, "Hide (E)yes", Point(0, 50), FONT_HERSHEY_SIMPLEX, 0.75, (0, 0, 0), 3);

        if (swapFlag)
        {
            putText(draw, "(S)wap Faces - Active", Point(0, 75), FONT_HERSHEY_SIMPLEX, 0.75, (0, 0, 0), 3);

            if (faces.size() > 1)
            {
                Rect otherFace = faces[1];

                Mat otherFaceArea = original(otherFace).clone();
                Mat mainFaceArea = original(mainFace).clone();

                resize(mainFaceArea, mainFaceArea, otherFace.size());
                resize(otherFaceArea, otherFaceArea, mainFace.size());

                otherFaceArea.copyTo(draw(mainFace));
                mainFaceArea.copyTo(draw(otherFace));
            }
        }
        else
            putText(draw, "(S)wap Faces", Point(0, 75), FONT_HERSHEY_SIMPLEX, 0.75, (0, 0, 0), 3);

        if (circleFlag)
        {
            circle(draw, faceCenter, radius, (0, 0, 0));
            putText(draw, "(C)ircle - Active", Point(0, 100), FONT_HERSHEY_SIMPLEX, 0.75, (0, 0, 0), 3);
        }
        else
            putText(draw, "(C)ircle", Point(0, 100), FONT_HERSHEY_SIMPLEX, 0.75, (0, 0, 0), 3);

        key = waitKey(25);

        imshow("Edited", draw);

        facePoints.clear();

        if (key == 27)
            break;
    }

    original.release();
    gray.release();
    src.release();

    destroyAllWindows();

    return 0;
}