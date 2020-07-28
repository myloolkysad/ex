function selectMusic(o,size) {
	for(i=0;i<size;i++) {
		document.music.musicN[i].style.backgroundColor = "#ffffff";
	}
	o.style.backgroundColor = "#7FFF00";
	document.controller.musicName.value = o.value;
}

function what(value) {
	document.controller.waht.value=value;
	document.controller.submit();
}

function screen(z){
	//var aa = document.music.musicN[z].value;
	//localStorage.setItem('alpha', aa);
	//var name = localStorage.getItem('alpha');
	//document.getElementById('playing').innerHTML= name;
	document.getElementById('playing').innerHTML= document.getElementById('pp').value;
}