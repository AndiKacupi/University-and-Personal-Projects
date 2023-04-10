from flask import Flask, render_template, jsonify,send_file, request
from sqlalchemy import create_engine
from sqlalchemy import text
import simplejson as json # solved the decimal to json format problem
import psycopg2
import psycopg2.extras

#create the app
app = Flask(__name__)

# rendering the index or entry using either of the 3 routes
@app.route('/')
@app.route('/index')
@app.route('/home')
def index():
    return render_template('index.html')

# the bounding-box implementation/
@app.route('/bounding-box')
def buffer_point():
    # using the request function to get the values of lon, lat, and epsg
    lon = request.args.get('lon')
    lonn = request.args.get('lonn')
    lat = request.args.get('lat')
    latt = request.args.get('latt')
    epsg = request.args.get('epsg')

    # error_res dictionary is used to build the JSON response
    # in case there is an error with lon, lat, or epsg values
    error_res = {}

    # check if lon argument value is valid as numeric
    # arguments passed from the API are strings
    try:
        lon = float(lon)
    except ValueError:
        error_res['longitude error'] = 'lon argument should be numeric'
        error_res['value given'] = lon
        return jsonify(error_res)
    
    try:
        lonn = float(lonn)
    except ValueError:
        error_res['longitude error'] = 'lon argument should be numeric'
        error_res['value given'] = lon
        return jsonify(error_res)
    
    # check if lon is out of range
    if lon < -180.0 or lon > 180.0:
        error_res['longitude error'] = 'lon argument value out of range. It shoud be between -180.0 and 180.0'
        error_res['value given'] = lon
        return jsonify(error_res)

    # check if lat argument value is valid as numeric
    # arguments passed from the API are strings
    try:
        lat = float(lat)
    except ValueError:
        error_res['latitude error'] = 'lat argument should be numeric'
        error_res['value given'] = lat
        return jsonify(error_res)
    
    try:
        latt = float(latt)
    except ValueError:
        error_res['latitude error'] = 'lat argument should be numeric'
        error_res['value given'] = lat
        return jsonify(error_res)

    # check if lat is out of range
    if lat < -90.0 or lat > 90.0:
        error_res['latitude error'] = 'lat argument value out of range. It shoud be between -90.0 and 90.0'
        error_res['value given'] = lat
        return jsonify(error_res)

    # check if epsg argument value is valid as numeric
    # arguments passed from the API are strings
    try:
        epsg = int(epsg)
    except ValueError:
        error_res['epsg error'] = 'epsg argument should be numeric'
        error_res['value given'] = epsg
        return jsonify(error_res)

    # check if epsg is wrong
    if  epsg != 4326:
        error_res['buffer distance error'] = 'this API expects a buffer distance = 4236'
        error_res['value given'] = epsg
        return jsonify(error_res)

    #connect to db
    conn = psycopg2.connect("dbname=geo_db host=localhost user=postgres password=root")
    cur = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)

    #query to db
    cur.execute ('SELECT *,ST_AsText(geom) as geom_WKT FROM geodep_4326 WHERE geom && ST_MakeEnvelope(%f,%f,%f,%f,%d)' % (lon, lonn, lat, latt, epsg))
    ans =cur.fetchall()

    #arrange data to json format
    ans1 = []
    for row in ans:
        ans1.append(dict(row))
        with open('data.json', 'w', encoding='utf-8') as f:
            json.dump(ans1, f, ensure_ascii=False, indent=4)
    return jsonify(ans1)

    #connect to db
    #engine = create_engine('postgresql://postgres:root@localhost:5432/geo_db')
    #connection = engine.connect()

    #query to db
    #my_query = ('SELECT *,ST_AsText(geom) as geom_WKT FROM geodep_4326 WHERE geom && ST_MakeEnvelope(%f,%f,%f,%f,%d)' % (lon, lonn, lat, latt, epsg))
    #results = connection.execute(text(my_query)).fetchall()

    #json_format = json.dumps(results,indent=4, sort_keys=True)
    #return jsonify(json_format)

@app.route('/download')
def download():
    path = 'data.json'
    return send_file(path, as_attachment=True)  
    
# main to run app
if __name__ == '__main__':
    app.run(debug = True)