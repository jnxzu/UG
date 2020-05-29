#include <opencv2/highgui.hpp>
#include <opencv2/video.hpp>

#include <iostream>
#include <time.h>
#include <string>
#include <algorithm>

using namespace cv;

VideoWriter writer;
clock_t recStartTime, cooldownStart;
bool recording = false;
Mat original, motion;
std::string recStartString;
char *dt;
int codec = VideoWriter::fourcc('M', 'J', 'P', 'G');

bool startWriter()
{
    try
    {
        recording = true;
        recStartTime = clock();
        recStartString.assign(dt);
        std::replace(recStartString.begin(), recStartString.end(), ':', '-');
        recStartString.erase(std::remove(recStartString.begin(), recStartString.end(), '\n'), recStartString.end());
        recStartString.append(".avi");
        writer.release();
        writer.open(recStartString, codec, 10, original.size());
    }
    catch (const std::exception &e)
    {
        return false;
    }
    return true;
}

std::vector<std::vector<Point>> detectMotion(Mat image, Ptr<BackgroundSubtractorMOG2> mog)
{
    mog->apply(image, motion);
    mog->setHistory(100);
    mog->setNMixtures(5);

    threshold(motion, motion, 120, 255, 0);

    std::vector<std::vector<Point>> contours;

    findContours(motion, contours, RETR_EXTERNAL, 2);

    return contours;
};

int main()
{
    VideoCapture cam;
    int input = 0;
    bool cooldown = true;
    Ptr<BackgroundSubtractorMOG2> mog = createBackgroundSubtractorMOG2();
    std::vector<std::vector<Point>> contours;

    if (!cam.open(0))
        return -1;

    cooldownStart = clock();

    while (true)
    {
        cam >> original;

        if (!cooldown)
            contours = detectMotion(original, mog);

        time_t now = time(0);
        dt = ctime(&now);

        putText(original, dt, Point(0, original.rows - 20), 0, 0.5, Scalar(0, 255, 0), 2);

        if (cooldown)
        {
            putText(original, "Cooldown", Point(0, 20), 0, 0.5, Scalar(255, 0, 0), 2);
            if ((double)(clock() - cooldownStart) / CLOCKS_PER_SEC >= 5.0)
                cooldown = false;
        }

        if (!cooldown && !recording)
        {
            if (contours.size() > 0)
                if (!startWriter())
                    return -1;
                else
                    putText(original, "No Motion", Point(0, 20), 0, 0.5, Scalar(255, 255, 255), 2);
        }

        if (recording)
        {
            putText(original, "Recording", Point(0, 20), 0, 0.5, Scalar(0, 0, 255), 2);
            writer.write(original);
            if ((double)(clock() - recStartTime) / CLOCKS_PER_SEC >= 10.0)
            {
                recording = false;
                cooldown = true;
                cooldownStart = clock();
            }
        }

        imshow("original", original);

        input = waitKey(30);

        if (input == 32)
        {
            if (recording)
            {
                recording = false;
                cooldown = true;
                cooldownStart = clock();
            }
            else
            {
                cooldown = false;
                if (!startWriter())
                    return -1;
            }
        }

        if (input == 27)
            break;
    }

    return 0;
}