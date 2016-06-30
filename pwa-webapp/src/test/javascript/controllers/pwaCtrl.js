describe('pwaApp', function() {

    var scope;

    beforeEach
    (function() {
        module('pwaApp');
    });

    var $httpBackend,
        findAllCityUrl = '/services/weather/findAllCity',
        getWeatherInfoUrl = '/services/weather/weatherInfo/';

    beforeEach(inject(function($rootScope, $controller, _$httpBackend_) {
        $httpBackend = _$httpBackend_;
        scope = $rootScope.$new();
        $controller('pwaCtrl', {
            '$scope': scope
        });
    }));

    it('successfully request find all city', function() {
        var data = {
            "status": "Success",
            "message": null,
            "url": null,
            "object": [{ "id": "1", "searchId": 2147714, "name": "Sydney", "country": "Australia" }, {
                "id": "2",
                "searchId": 2158177,
                "name": "Melbourne",
                "country": "Australia"
            }, { "id": "3", "searchId": 2171507, "name": "Wollongong", "country": "Australia" }]
        };

        $httpBackend.expectGET(findAllCityUrl).respond(200, data);

        $httpBackend.flush();

        expect(scope.cityName[0].name).toBe("Sydney");
    });

    it('failure find all city with error message return', function() {
        var data = {
            "status": "Failure",
            "message": "failure message",
            "url": null,
            "object": [{ "id": "1", "searchId": 2147714, "name": "Sydney", "country": "Australia" }, {
                "id": "2",
                "searchId": 2158177,
                "name": "Melbourne",
                "country": "Australia"
            }, { "id": "3", "searchId": 2171507, "name": "Wollongong", "country": "Australia" }]
        }

        $httpBackend.expectGET(findAllCityUrl).respond(200, data);

        $httpBackend.flush();

        expect(scope.infoError).toBe("failure message");
    });

    it('successfully request and return weather information', function() {
        var data = {
            "status": "Success",
            "message": null,
            "url": null,
            "object": {
                "weather": [{ "main": "Clear", "description": "clear sky" }],
                "main": { "temp": "10.58" },
                "wind": { "speed": "8.7" },
                "dt": "1467300510",
                "id": "2147714",
                "name": "Sydney"
            }
        };
        scope.formData = { selectedCityName: "Sydney" };
        scope.onWeatherFind();

        $httpBackend.expectGET(findAllCityUrl).respond(200, data);

        $httpBackend.expectGET(getWeatherInfoUrl + scope.formData.selectedCityName).respond(200, data);

        $httpBackend.flush();

        expect(scope.infoError).toBe("");

        expect(scope.weatherInfo.main.temp).toBe("10.58");
        expect(scope.weatherInfo.name).toBe("Sydney");

    });

    it('successfully return weather info error message', function() {
        var data = {
            "status": "Failure",
            "message": "Failure Message",
            "url": null,
            "object": {
                "weather": [{ "main": "Clear", "description": "clear sky" }],
                "main": { "temp": "10.58" },
                "wind": { "speed": "8.7" },
                "dt": "1467300510",
                "id": "2147714",
                "name": "Sydney"
            }
        };
        scope.formData = { selectedCityName: "Sydney" };
        scope.onWeatherFind();

        $httpBackend.expectGET(findAllCityUrl).respond(200, data);

        $httpBackend.expectGET(getWeatherInfoUrl + scope.formData.selectedCityName).respond(200, data);

        $httpBackend.flush();

        expect(scope.infoError).toBe("Failure Message");
        expect(scope.weatherInfo).toBe(undefined);

    });


    it('return error message when empty form data', function() {

        scope.formData = '';

        scope.onWeatherFind();

        expect(scope.infoError).toBe("Please select a city then click the search button");
        expect(scope.weatherInfo).toBe(undefined);

    });


    it('return error message when find all city service page error', function() {

        scope.formData = { selectedCityName: "Sydney" };
        scope.onWeatherFind();

        $httpBackend.expectGET(findAllCityUrl).respond(500, '');

        $httpBackend.expectGET(getWeatherInfoUrl + scope.formData.selectedCityName).respond(200, '');

        $httpBackend.flush();

        expect(scope.infoError).toBe("Server Request Error");
        expect(scope.weatherInfo).toBe(undefined);

    });

    it('return error message when weather info service page error', function() {

        scope.formData = { selectedCityName: "Sydney" };
        scope.onWeatherFind();

        $httpBackend.expectGET(findAllCityUrl).respond(200, '');

        $httpBackend.expectGET(getWeatherInfoUrl + scope.formData.selectedCityName).respond(400, '');

        $httpBackend.flush();

        expect(scope.infoError).toBe("Server Request Error");
        expect(scope.weatherInfo).toBe(undefined);

    });
});

