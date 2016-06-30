'use strict';
angular.module("pwaApp").controller('LoginCtrl', function($scope, $http, $window) {

    $scope.onLogin = function() {

        $scope.login($scope.formData.username, $scope.formData.password);

    };


    $scope.preparePostData = function() {
        var username = $scope.formData.username != undefined ? $scope.formData.username : '';
        var password = $scope.formData.password != undefined ? $scope.formData.password : '';

        return 'username=' + username + '&password=' + password;
    }

    $scope.login = function() {

        $scope.errorInfo = null;

        var postData = $scope.preparePostData();

        $http({
            method: 'POST',
            url: '/authenticate',
            data: postData,
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
        }).success(function(data) {
            if (data.status === 'Success' && !data.message) {
                $window.location.href = data.url;
            }
            else {
                $scope.errorInfo = data.message;
            }

        }).error(function()
        {
            $scope.infoError = 'Server Request Error';

        });
    }

});
