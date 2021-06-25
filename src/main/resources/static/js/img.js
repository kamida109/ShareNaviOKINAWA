

const imageList = document.getElementsByClassName("image");


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

