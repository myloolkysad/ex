function showPage(n){
	var list1;
	var list2;
	var list3;

	if(n == 1){
		var i=0;
		while(i<19){
			list1 = document.getElementById("list"+i);
			list1.style.display = "";
			i++;
		}
		while(i<38){
			list2 = document.getElementById("list"+i);
			list2.style.display = "none";
			i++;
		}
		while(i<50){
			list3 = document.getElementById("list"+i);
			list3.style.display = "none";
			i++;
		}
	}else if(n == 2){
		var i=0;
		while(i<19){
			list1 = document.getElementById("list"+i);
			list1.style.display = "none";
			i++;
		}
		while(i<38){
			list2 = document.getElementById("list"+i);
			list2.style.display = "";
			i++;
		}
		while(i<50){
			list3 = document.getElementById("list"+i);
			list3.style.display = "none";
			i++;
		}
	}else if(n == 3){
		var i=0;
		while(i<19){
			list1 = document.getElementById("list"+i);
			list1.style.display = "none";
			i++;
		}
		while(i<38){
			list2 = document.getElementById("list"+i);
			list2.style.display = "none";
			i++;
		}
		while(i<50){
			list3 = document.getElementById("list"+i);
			list3.style.display = "";
			i++;
		}
	}
}


