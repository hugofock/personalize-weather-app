'use strict';
angular.module("pwaApp").controller("pwaCtrl", function($scope, $http) {

    $scope.infoError = "";

    $http.get('/services/weather/findAllCity').success(function(data) {
        if (data.status === "Success") {
            $scope.cityName = data.object;
        }
        else if (data.status === "Failure") {
            $scope.infoError = data.message;
        }
    }).error(function()
    {
        $scope.infoError = 'Server Request Error';

    });

    $scope.onWeatherFind = function() {

        if ($scope.formData) {
            $scope.infoError = "";
            $http.get('/services/weather/weatherInfo/' + $scope.formData.selectedCityName).success(function(data) {
                if (data.status === "Success") {
                    $scope.weatherInfo = data.object;
                }
                else if (data.status === "Failure") {
                    $scope.infoError = data.message;
                }
            }).error(function()
            {
                $scope.infoError = 'Server Request Error';

            });
        } else {
            $scope.infoError = "Please select a city then click the search button";
        }

    };

});