var vuedemo01 = new Vue({
	el : '#app-1',
	data : {
		seen : true,
		seen1 : "",
		boxvalue : ["uber"]
	},
	methods : {
		viewValues : function(){
			alert(this.seen);
		},
		change : function(){
			this.seen = this.seen1;
		},
		click1 : function(){
			this.$http.jsonp('http://localhost:8080/test/method',
					{params:{a:1,b:20},jsonp:'callback123'}).then(
          function(response){ //成功的回调函数
              console.log(response)
              alert("成功："+response.status+";"+response.bodyText+";"+response.data.A);
          },
          function(response){ //失败的回调函数
              console.log(response)
              alert("失败："+response.status);
          }
      )
		}
	}
});