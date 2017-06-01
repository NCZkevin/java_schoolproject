angular.module('starter.controllers', [])

.controller('AppCtrl', function ($scope, $ionicModal, $timeout) {

    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    //$scope.$on('$ionicView.enter', function(e) {
    //});


})

.controller('ChatsCtrl', function ($scope, Chats, $ionicLoading,$http) {
    // With the new view caching in Ionic, Controllers are only called
    // when they are recreated or on app start, instead of every page change.
    // To listen for when this page is active (for example, to refresh data),
    // listen for the $ionicView.enter event:
    //
    //$scope.$on('$ionicView.enter', function(e) {
    //});

    $ionicLoading.show({
        template: 'loading'
    });
    $http.get("http://localhost:8081/person").then(function (response) {
        $scope.chats = response.data
    },function (error) {
        console.log(error);
    });

    $ionicLoading.hide();

    // Chats.load(function () {

    //     // $scope.chats = Chats.all();
    //     $scope.remove = function (chat) {
    //         Chats.remove(chat);
    //     };

    //     $scope.search = function () {
    //         $scope.chats = Chats.search($scope.query);
    //     }

    //     $ionicLoading.hide();
    // });
})

.controller('ChatDetailCtrl', function ($scope, $stateParams, Chats,$http) {
    // $scope.chat = Chats.get($stateParams.chatId);
    $http.get("http://localhost:8081/personinfo/username/"+$stateParams.studentNum).then(function (response) {
        $scope.chat = response.data
    },function (error) {
        console.log(error);
    });
})
.controller('LoginCtrl', function ($scope, $stateParams, Chats,$http) {
    $scope.signin = function() {
        $http({
          method: 'POST',
          url: "http://localhost:8081/user/login1",
          headers: { 'Content-Type': 'application/json; charset=UTF-8'},
          data: {
              username: $scope.username,
              password: $scope.password
          }
          ,
          transformRequest: function (data) {
            return JSON.stringify(data)
          }
        })
        // $http.post(baseUrl + $scope.username + '&password=' + $scope.password,{headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'}})
        // $http.post("http://127.0.0.1:8081/user/login",{
        //       username: $scope.username,
        //       password: $scope.password
        //    })
        .then(
          function(response){
            token = response.data.token;
            console.log(response, token);
            localStorage.token = token;
            localStorage.setItem('username', response.data.name)
            localStorage.setItem('id', response.data.user.id)
            localStorage.setItem('permission', response.data.user.permission)
            setTimeout(function () {
              window.location = "/#/app/employees";
            },50);
          },
          function (error) {
            console.log(error);
            alert("登录错误，请检查密码！或者服务器错误");
          }
         )
    }
})

.controller('SettingsCtrl', function ($scope, Chats, $ionicLoading) {
    $scope.refresh = function () {

        $ionicLoading.show({
            template: 'loading'
        });

        Chats.refresh(function () {
            $ionicLoading.hide();
        });
    }
})
;
