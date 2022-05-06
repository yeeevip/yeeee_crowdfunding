window.calender = (function(win,doc){
	function C(str,b){
		this.dom =  (typeof str=='object') ? str : doc.querySelector(str);  
		this.s = {
			date : [ new Date().getFullYear(),new Date().getMonth()+1,new Date().getDate()],
			button : false,
			format : 'yyyy年MM月dd日',
			left : 0,
			top: 0,
			addClass : '',
			onload : function(){}
		}
		this.inline = b ? true : false
	};
	C.prototype = {
		init : function(){
			var t = this;
			if( typeof arguments[0] == 'function'){
	    		t.cb = arguments[0];
	    	}else{
	    		t.newS = arguments[0];
	    		t.cb = arguments[1] || function(){}
	    	};
	    	t.yoff = false;
	    	t.moff = false;
			t.extend(t.s,t.newS);
			t.nt = new Date();
			t.nt.setFullYear(t.s.date[0]);
		  	t.nt.setMonth(t.s.date[1]-1);
		  	var len = this.getDateLength(t.nt.getFullYear(),t.nt.getMonth()  )
		  	t.nt.setDate(t.s.date[2]>len ? len : t.s.date[2]);
		  	t.day = t.nt.getDate();
		  	if(t.inline){
		  		t.create();
				t.bind();
				t.s.onload.call(t.dom)
		  	}else{
				t.dom.onclick = function(ev){
					var e = ev || event;
					t.create();
					t.bind();
					t.s.onload.call(this)
					e.stopPropagation ? e.stopPropagation() : (e.cancelBubble = true)
				};	
			}
		},
		hide : function(){
			var t = this;
			t.cb.call(t.dom,t.format( t.nt.getFullYear()+'/'+ (t.nt.getMonth()+1)+'/'+ t.day+' '+new Date().getHours()+':'+new Date().getMinutes()+':'+new Date().getSeconds(),t.s.format));
			if( g('.calendar-wrap')) doc.body.removeChild( g('.calendar-wrap') ),doc.body.removeChild( g('#calendar-mask') );
		},
		bind : function(){
			var t = this;
			var Content = g('.calendar-content');
			t.createDay();
			var Prev = g('#calendar-prev'),
				Next = g('#calendar-next'),
				Year = g('#calendar-year'),
				Mon =  g('#calendar-mon');
			if(t.s.button){
				var today = g('.calendar-today');
				var enter = g('.calendar-ent');
				today.onclick = function(){
					t.nt.setFullYear(new Date().getFullYear());
				  	t.nt.setMonth(new Date().getMonth());
				  	t.nt.setDate( new Date().getDate());
				  	t.s.date[2] = t.day = new Date().getDate()
					t.createYear()
					t.createDay()
					t.createMon()
				};
				enter.onclick = function(){
					t.hide();
				}
			}
			Content.onclick = function(ev){
				var ev = ev || event;  
		    	var _target = ev.target || ev.srcElement; 
		    	if(!t.has(_target,'calendar-cell-dark') ){
		    		var chl = this.children;
		    		for(var i = 0;i<chl.length;i++){
		    			t.del(chl[i],'active');
		    		};
		    		t.add(_target,'active');
		    		t.nt.setDate(_target.getAttribute('data-n'));
		    		t.s.date[2] = t.day = _target.getAttribute('data-n')
		    		if(!t.s.button){
		    			t.hide();
		    		}
		    	}
			}
			Prev.onclick = Next.onclick = function(){
				var y = t.nt.getFullYear(),m = t.nt.getMonth();
				if(t.moff) return
				if(t.yoff){
					t.nt.setFullYear( this.id=="calendar-prev" ? y -= 9 :  y += 9)
					t.createYear()
				}else{
					this.id=="calendar-prev" ? m-- : m++;
					t.nt.setDate(1);
					t.nt.setMonth( m );
					t.createDay()
				}
			}
			Year.onclick = function(){
				t.createYear();
				t.yoff = true;
				t.moff = false;
				t.del(g('.calendar-wrap'),'month');
				t.add(g('.calendar-wrap'),'year');
			};
			Mon.onclick = function(){
				t.createMon();
				t.moff = true;
				t.yoff = false;
				t.del(g('.calendar-wrap'),'year');
				t.add(g('.calendar-wrap'),'month');
			};
		},
		getDateLength : function(year,month){
			//获取某一月有多少天, month为实际月份，一月即为1
			return new Date(year,month,0).getDate();
		},
		getFirstDay : function(year,month){
			//获取某一月第一天是周几,month为实际月份，一月即为1,返回0即为周日
			return new Date(year,month-1,0).getDay();
		},
		createMon : function(){
			var t= this,html='';
			var m = t.nt.getMonth()+1;
			m = m == 0 ? 12 : m;
			for(var i = 1;i<=12;i++){
				html+='<div class="calendar-mon-cell '+( m == i ? 'active' : '') +' ">'+ (i) +'</div>';
			};
			g('.calendar-list3').innerHTML = html;
			var cells = doc.querySelectorAll('.calendar-mon-cell');
			for(var i2 = 0;i2<cells.length;i2++){
			 	cells[i2].onclick = function(){
			 		t.moff = false
			 		t.del(g('.calendar-wrap'),'month');
			 		t.nt.setDate(1)
					t.nt.setMonth(+this.innerHTML-1);
					t.createDay();
			 	}
			}
		},
		createYear : function(){
			var t= this,html='',y = (t.nt.getFullYear());
			var Year = g('#calendar-year');
			for(var i = 0;i<9;i++){
				html+='<div class="calendar-year-cell '+( (y-(4-i)) == y ? 'active' :'') +' ">'+ (y-(4-i)) +'</div>';
			}
			Year.innerHTML = y
			g('.calendar-list2').innerHTML = html;
			var cells = doc.querySelectorAll('.calendar-year-cell');
			for(var i2 = 0;i2<cells.length;i2++){
			 	cells[i2].onclick = function(){
			 		t.yoff = false;
			 		t.del(g('.calendar-wrap'),'year');
					t.nt.setFullYear(+this.innerHTML);
					t.createDay();
			 	}
			}
		},
		createDay : function(n){
			var t = this, 
				y = t.nt.getFullYear(),
				m = (t.nt.getMonth())+1;
			g('#calendar-year').innerHTML = m===0 ? y-1 : y;
			g('#calendar-mon').innerHTML =  m === 0 ? 12 : two(m);
			// if(t.nt.getMonth()+1 == t.s.date[1] && t.nt.getFullYear()==t.s.date[0]   ){
			//  	t.nt.setDate(t.s.date[2]);
			// };
			var firstDay = this.getFirstDay(y,m),
				length = this.getDateLength(y,m),
				lastMonthLength = this.getDateLength(y,m-1),
				i,html = '';
				t.day = t.s.date[2] > length ? length : t.s.date[2];
			//循环输出月前空格
			if(firstDay ===0) firstDay = 7;
			for(i=1;i<firstDay+1;i++){
				html += '<div class="calendar-cell calendar-cell-dark">' + (lastMonthLength - firstDay + i) + '</div>';
			}
			//循环输出当前月所有天
			for(i=1;i<length+1;i++){
				html += '<div  data-n='+i+' class="calendar-cell '+ (i == t.day ? 'active' :'') +'">' + i + '</div>';
			}
			//if(8-(length+firstDay)%7 !=8){
			for(i=1;i<= (41-(length+(firstDay==0 ? 7 : firstDay)-1));i++){
				html+= '<div class="calendar-cell calendar-cell-dark">' + i + '</div>';
			};
			doc.querySelector('.calendar-content').innerHTML = html
		},
		create : function(){
			var t= this;
			if( g('.calendar-wrap')) doc.body.removeChild( g('.calendar-wrap') )
			var private_Day_title=['一','二','三','四','五','六','日'];
			//内容
			var html = '<div class="calendar-wrap '+ t.s.addClass +'">';
			html +='<div id="calendar-header" class="calendar-header none-btn "><a id="calendar-prev" href="javascript:;">&lt;</a><a id="calendar-next" href="javascript:;">&gt;</a>  <span id="calendar-year">2016</span>年<span id="calendar-mon">10</span>月</div>'
			//星期
			html += '<div class="calendar-list"><div class="calendar-caption">';
			for(i=0;i<7;i++){
				html += '<div class="calendar-cell">' + private_Day_title[i] + '</div>';
			};
			html += '</div><div class="calendar-content"></div>';
			if(this.s.button){
				html+='<div class="calendar-button"><a href="javascript:;" class="calendar-ent">确定</a><a href="javascript:;" class="calendar-today">今天</a></div>';
			};
			html += '</div><div class="calendar-list calendar-list2"></div><div class="calendar-list calendar-list3"></div>'
			doc.body.insertAdjacentHTML("beforeend", html);
			doc.body.insertAdjacentHTML("beforeend", '<div id="calendar-mask"></div>');
			var wrap = g('.calendar-wrap');
			function setPosi(){
				var _top = doc.documentElement.scrollTop || doc.body.scrollTop;
				wrap.style.left = t.dom.getBoundingClientRect().left +t.s.left +'px';;
				wrap.style.top = t.dom.getBoundingClientRect().top + _top + t.dom.offsetHeight+t.s.top + 15 +'px';
			};
			setPosi();
			addEvent(window,'resize',function(){setPosi()})
			wrap.onclick = function(ev){
				var e = ev || event;
				e.stopPropagation ? e.stopPropagation() : (e.cancelBubble = true)
			}
			g('#calendar-mask').onclick = function(ev){
				t.hide()
			}
		},
	    format : function(da,format){
	     	var _newDate = new Date(da);
	 		var WeekArr=['星期日','星期一','星期二','星期三','星期四','星期五','星期六']; 
		    var o = {  
		        'M+' : _newDate.getMonth()+1, //month  
		        'd+' : _newDate.getDate(), //day  
		        'h+' : _newDate.getHours(), //hour  
		        'm+' : _newDate.getMinutes(), //minute  
		        's+' : _newDate.getSeconds(), //second  
		        'q+' : Math.floor((_newDate.getMonth()+3)/3), //quarter  
		        'S': _newDate.getMilliseconds(), //millisecond  
		        'E': WeekArr[_newDate.getDay()],  
		        'e+' : _newDate.getDay()  
		    };   
		    if (/(y+)/.test(format)){
		    	format = format.replace(RegExp.$1, (_newDate.getFullYear()+"").substr(4 - RegExp.$1.length));
		    };
		    for(var k in o) {  
		        if(new RegExp('('+ k +')').test(format)) {  
		            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ('00'+ o[k]).substr((''+ o[k]).length));  
		        };  
		    };  
		    return format; 
	    },
	    extend : function(n,n1){
	        for(var i in n1){n[i] = n1[i]};
	    },
		has : function(o,n){
		    return new RegExp('\\b'+n+'\\b').test(o.className);  
	    },
		add : function(o,n){
		    if(!this.has(o, n)) o.className+=' '+n;  
	    },
		del : function(o,n){
		    if(this.has(o, n)){  
		        o.className = o.className.replace(new RegExp('(?:^|\\s)'+n+'(?=\\s|$)'), '').replace(/^\s*|\s*$/g, '');  
		    }; 
	    }
	};
	function g(str){return doc.querySelector(str)};
	function addEvent(obj,name,fn){obj.addEventListener? obj.addEventListener(name, fn, false):obj.attachEvent('on'+name,fn);};
    function two(num){return num<10 ? ('0'+num) : (''+num)};
	function c(o,b){return new C(o,b)};return c;
})(window,document);