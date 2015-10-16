var express = require('express');
var http = require('http');
var fs = require("fs");
var obj;
var app = express();

const PORT = 8081;

app.get('/', function (req, req) {
    req.send("This local host is for testing Node JS");
});

app.get('/users/testificate/data.json', function (req, req) {
    req.setHeader('Content-Type', 'application/json');
    req.send(JSON.parse(fs.readFileSync("./users/testificate/data.json")));
});

app.get('/test.json', function (req, req) {
    req.setHeader('Content-Type', 'application/json');
    req.send((JSON.parse(fs.readFileSync("./test.json"))));
});

var server = app.listen(PORT, function () {
    console.log("Server listening on: http://localhost:%s", PORT);
});