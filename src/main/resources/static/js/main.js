angular.module("chatDemo", [])
       // Chat Controller
       .controller("ChatCtrl", function($scope, $timeout, ChatService) {
    	    $scope.messages = [];
    	    $scope.message = "";
    	    $scope.from = ''; 
    	    $scope.max = 140;
    	    
    	    $scope.connected = false;
    	    
    	    $scope.connect = function() {
    	      ChatService.connect($scope.from);
    	      $scope.connected = true;
    	    };
    	    
    	    /**
    	     * Send message
    	     */
    	    $scope.sendMessage = function() {
    	      ChatService.send($scope.from, $scope.message);
    	      $scope.message = "";
    	    };
    	    
    	    /**
    	     * Message listener
    	     */
    	    ChatService.receive().then(null, null, function(message) {
    	      // Add message to the list.	
    	      $scope.messages.push(message);
    	      // Scroll down to the most resent message.
    	      $timeout(function() {
    	          var scroller = document.getElementById("messages");
    	          scroller.scrollTop = scroller.scrollHeight;
    	        }, 0, false);
    	    });
       })
       // Chat Service
       .service("ChatService", function($q, $timeout) {
    
    	    
		    var service = {}
		    var listener = $q.defer()
		    var socket = {
			      client: null,
			      stomp: null
			    }
		    
		    service.receive = function() {
		      return listener.promise;
		    };
		    
		    /**
		     * Send message
		     */
		    service.send = function(from, message) {
		      socket.stomp.send("/app/chat", {
		        priority: 9
		      }, JSON.stringify({
		        text: message,
		        from: from
		      }));
		    };
		    
		    /**
		     * Reconnect
		     */
		    var reconnect = function() {
		      $timeout(function() {
		        initialize();
		      }, 30000);
		    };
		    
		    /**
		     * Parse the message received from server.
		     */
		    var parseMessage = function(data) {
		      var message = JSON.parse(data)
		      return message;
		    };
		    
		    /**
		     * Listener
		     */
		    var startListener = function() {
		      socket.stomp.subscribe("/topic/messages", function(data) {
		        listener.notify(parseMessage(data.body));
		      });
		    };
		    
		    /**
		     * Connects to the websocket
		     */
		    var initialize = function(username) {
		      socket.client = new SockJS("/chat");
		      socket.stomp = Stomp.over(socket.client);
		      socket.stomp.connect({ "username" : username}, startListener);
		      socket.stomp.onclose = reconnect;
		    };
		    
		    service.connect = function(username) {
		    	initialize(username);
		    }
		    
		    
		    return service;
	});
  
  