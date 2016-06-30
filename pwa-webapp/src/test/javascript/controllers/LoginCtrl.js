describe('pwaApp', function() {

    var scope;

    beforeEach
    (function() {
        module('pwaApp');
        angular.mock.module('pwaApp', function($provide) {
            $provide.value('$window', { location: { href: 'dummy' } });
        });
    });

    var $httpBackend,
        loginUrl = '/authenticate';

    beforeEach(inject(function($rootScope, $controller, _$httpBackend_) {
        $httpBackend = _$httpBackend_;
        scope = $rootScope.$new();
        $controller('LoginCtrl', {
            '$scope': scope
        });
    }));

    it('successfully user login', function() {
        var data = { "status": "Success", "message": "", "url": '/', "object": null };

        scope.formData = {
            username: 'test', password: 'password'
        };

        scope.preparePostData();
        scope.onLogin();

        $httpBackend.expectPOST(loginUrl, scope.preparePostData, function(headers) {
            return headers['Content-Type'] == 'application/x-www-form-urlencoded';
        }).respond(201, data);

        $httpBackend.flush();


        expect(scope.errorInfo).toBe(null);

        expect(window.location.pathname).toBe("/");

    });


    it('failure user login return error message', function() {
        var data = { "status": "Failure", "message": "The email and password you entered don't match.", "url": null, "object": null };

        scope.formData = {
            username: 'test', password: 'password'
        };

        scope.preparePostData();
        scope.onLogin();

        $httpBackend.expectPOST(loginUrl, scope.preparePostData, function(headers) {
            return headers['Content-Type'] == 'application/x-www-form-urlencoded';
        }).respond(201, data);

        $httpBackend.flush();

        expect(scope.errorInfo).toBe("The email and password you entered don't match.");


    });

});

