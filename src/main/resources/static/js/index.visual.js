window.addEventListener("load", function (event) {
			
    var bannerList = document.querySelector(".banner");
    var banners = bannerList.getElementsByTagName("li");
    
    bannerList.onclick = function(e){
    	var img = e.target;
    	var courseId = img.dataset.id;
    	
    	location.href = "course/"+courseId;
    };
    
    
    if(banners.length > 1)
    	timerStart();
            
});                         
            
            /*// 미디어 상태가 바뀔 때마다 동적으로 대처해야 할 경우에 사용하는 코드
            window.matchMedia("(max-width:640px)").addListener(function (e) {
                if (e.matches) {
                    //headerSection.classList.add("hide");
                    //mainMenu.classList.add("hide");
                }
            });
            window.matchMedia("(min-width:641px)").addListener(function (e) {
                if (e.matches) {
                    //headerSection.classList.remove("hide");
                    //mainMenu.classList.remove("hide");
                }
            });
            window.matchMedia("(min-width:768px) and (max-width:1024px)").addListener(function (e) {
                if (e.matches) {
                    //alert("b");
                }
            });
            window.matchMedia("(min-width:1024px)").addListener(function (e) {
                if (e.matches) {
                    //alert("c");
                }
            });
*/
        