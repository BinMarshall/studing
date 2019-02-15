var app1 = new Vue({
	el : '#app1',
	data : {
		dataaaa : {
			text : 'hello'
		}
	}
});



var app3 = new Vue({
	el : '#app3',
	data : {
		textValue : 'bravolinks',
		boxvalue : ['didi','uber'],
		radiovalue : 'xiaomi'
	},
	methods : {
		showValue : function(){
			alert(this.textValue);
			this.boxvalue = ['kuaidi'];
			this.radiovalue = 'huawei';
		}
	}
});

var app4 = new Vue({
	el : '#app4',
	data : {
		mylist : [
			{"name" : "jd", "age" : 10},
			{"name" : "al", "age" : 20},
			{"name" : "sn", "age" : 15}
		]
	}
});

var app5 = new Vue({
	el : '#app5',
	data : {
		textContent : '中科软科技股份有限公司',
		htmlContent : '<div id="app2">中青博联<p v-if="condition">A</p><p v-else="condition">B</p></div>'
	}	
});

var app2 = new Vue({
	el : '#app2',
	data : {
		condition : true
	}	
});

var app6 = new Vue({
	el : '#app6',
	data : {
		typevalue : 1,
		bindvalue : 'text'
	},
	methods : {
		changetype : function(){
			alert(this.typevalue);
			if(this.typevalue == 1){
				this.bindvalue = 'text';
			}else if(this.typevalue == 2){
				this.bindvalue = 'checkbox';
			}
		}
	}
});

var app7 = new Vue({
	el : '#app7',
	data : {
		sex : {
			name : '男'
		}
	},
	methods : {
		focusMethod : function(){
			alert(1);
		}
	}
});

var app8 = new Vue({
	el : '#app8',
	data : {
		name : 'js',
		age : 20
	},
	computed : {
		nameage : {
			cache : false,
			get : function(){
				return this.name + ":" + this.age + ":" + Date.now() + ":" + app7.sex.name;
			}
		}
	},
	methods : {
		showme : function(){
			alert(this.nameage);
		}
	}
});

var app13 = new Vue({
			el : "#app-13",
			data : {
				mylist : []
			},
			methods : {
				clickme2 : function(){
					this.$http.jsonp('http://localhost:8080/test/method',
							{params:{a:1,b:20},jsonp:'callback123'}).then(
              function(response){ //成功的回调函数
              	this.mylist = response.data;
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