#include "TrackedMass.h"

TrackedMass::TrackedMass(vector<Point> contour)
{
    this->contour = contour;
}

Point TrackedMass::getCenter()
{
    return this->center;
}

void TrackedMass::setCenter()
{
    Moments moment = moments(this->contour);
    this->center = Point2f(moment.m10 / moment.m00, moment.m01 / moment.m00);
}

vector<Point> TrackedMass::getContour()
{
    return this->contour;
}