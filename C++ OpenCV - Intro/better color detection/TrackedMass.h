#ifndef TM
#define TM

#include <opencv2/highgui.hpp>
#include <opencv2/imgproc.hpp>

using namespace cv;
using namespace std;

class TrackedMass
{
private:
    vector<Point> contour;
    Point center;

public:
    TrackedMass(vector<Point> contour);
    Point getCenter();
    void setCenter();
    vector<Point> getContour();
};

#endif