import requests
import googlemaps
import apikey as ak  # hidden from github


def getPath(start, end, samples=200):
    r = requests.get(
        url="https://maps.googleapis.com/maps/api/elevation/json?path={0},{1}|{2},{3}&samples={4}&key={5}".format(start[0], start[1], end[0], end[1], samples, ak.api_key))
    data = r.json()['results']
    height = [point['elevation'] for point in data]
    return list(range(samples)), height
