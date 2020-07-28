function showTabMenu(n) {
	var conId;
	var aa;
	for (i = 1; i < 4; i++) {
		conId = document.getElementById("con" + i);
		aa = document.getElementById("a" + i);
		if (i == n) {
			aa.style.backgroundImage = 'url(./resources/img/brickdark.png)';
			aa.style.borderRadius ="20px 20px 0px 0px";
			aa.style.width = "calc(98%)";
			aa.style.paddingBottom = "13px";
			aa.style.borderLeft = "2px solid #99A0AD";
			aa.style.borderRight = "2px solid #99A0AD";
			aa.style.borderTop = "1px solid #99A0AD";
			conId.style.display = "";
		} else {
			aa.style.backgroundImage = '';
			aa.style.borderRadius ="";
			aa.style.width = "";
			aa.style.paddingBottom = "";
			aa.style.borderLeft = '';
			aa.style.borderRight = '';
			aa.style.borderTop = '';
			conId.style.display = "none";
		}
	}
}