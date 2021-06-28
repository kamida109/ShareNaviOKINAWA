

const imageList = document.getElementsByClassName("image");
const imageList2 = document.getElementsByClassName("image2");
const imageList3 = document.getElementsByClassName("image3");

for (const imagess of imageList) {
	const images = imagess.children;

	for (const image of images) {
		image.addEventListener("click", e => {
			let num = Number(e.toElement.dataset.count);

			num++;
			if (e.toElement.getAttribute('data-img' + num) === undefined || e.toElement.getAttribute('data-img' + num) === null) {
				num = 1;
			}


			e.toElement.dataset.count = num;
			document.getElementById(e.toElement.id).src = e.toElement.getAttribute('data-img' + num);
		})
	}
}

for (const imagess2 of imageList2) {
	const images2 = imagess2.children;

	for (const image2 of images2) {
		image2.addEventListener("click", e => {
			let num = Number(e.toElement.dataset.count);

			num++;
			if (e.toElement.getAttribute('data-img' + num) === undefined || e.toElement.getAttribute('data-img' + num) === null) {
				num = 1;
			}


			e.toElement.dataset.count = num;
			document.getElementById(e.toElement.id).src = e.toElement.getAttribute('data-img' + num);
		})
	}
}

for (const imagess3 of imageList3) {
	const images3 = imagess3.children;

	for (const image3 of images3) {
		image3.addEventListener("click", e => {
			let num = Number(e.toElement.dataset.count);

			num++;
			if (e.toElement.getAttribute('data-img' + num) === undefined || e.toElement.getAttribute('data-img' + num) === null) {
				num = 1;
			}


			e.toElement.dataset.count = num;
			document.getElementById(e.toElement.id).src = e.toElement.getAttribute('data-img' + num);
		})
	}
}
