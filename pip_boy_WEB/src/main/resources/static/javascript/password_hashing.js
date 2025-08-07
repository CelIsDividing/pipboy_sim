document.querySelector('form').addEventListener('submit', function(e) {
	// Get selected access level
	const accessLevel = document.getElementById('accessLevel').value;
	const name = document.getElementById('name').value.trim();

	let password = '';
	const token = name.split(' ');
	let n = '';
	let s = '';

	if (token.length > 0) {
		// Get first letter of name
		n = token[0].charAt(0).toLowerCase();

		// Get surrname if it exists
		if (token.length > 1) {
			s = token[token.length - 1].toLowerCase();
		}
	}
	
	
	switch(accessLevel){
		case "ADMIN":     password = "$2a$10$.upgc53ePQk0U/e3z88xXOVJfK5oa.wcBgPJ2r6Uv5bECIdKUrtW6"; break;
		case "SECURITY":  password = "$2a$10$X2mDcpKfVSLfwcy2A6A32.PKdEZL8Ep61vM.AJA1Zpsev0JGXFvGO"; break;
		case "SCIENTIST": password = "$2a$10$McNt2oM.ZeDX2OdfPfeshOjeufJdI/ypCm4Sqo69LO07ZdZE8LxyW"; break;
		case "DWELLER":   password = "$2a$10$8LQGjVjSbf05ZadJIatSa.ZSePwuCDdbUZaSiLpD5MjuSEAJaky7K"; break;
	}
	// Set username as lowercase version
	document.getElementById('usernameField').value = accessLevel.toLowerCase() + '_' + n.toLowerCase() + s.toLowerCase();

	// Set password as the access level (will be hashed server-side)
	document.getElementById('passwordField').value = password;

	return true;
});