(function(angular) {
    var AppController = function($scope, $http) {

        $scope.foundWeather = false;

        $scope.infoError = "";

        $http.get('/services/weather/findAllCity').success(function(data) {
            if (data.status === "Success") {
                $scope.cityName = data.object;
            }
            else if (data.status === "Failure") {
                $scope.infoError = data.message;
            } else {
                $scope.infoError = "Unknown error";
            }
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
                    } else {
                        $scope.infoError = "Unknown error";
                    }
                });
            } else {
                $scope.infoError = "Please select a city then click the search button";
            }

        };

    };

    AppController.$inject = ['$scope', '$http'];
    angular.module("myApp.controllers").controller("AppController", AppController);

}(angular));