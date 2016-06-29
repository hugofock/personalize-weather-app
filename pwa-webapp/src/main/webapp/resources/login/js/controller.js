'use strict';
angular.module("pwaApp").controller('LoginCtrl', function($scope, $http) {

    $scope.response = {};

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
        }).then(function(response) {
            if (response.data.status === 'Success' && !response.data.message) {
                window.location.href = response.data.url;
            }
            else {
                $scope.errorInfo = response.data.message;
            }

        });
    }

});
