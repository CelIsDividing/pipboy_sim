// typing-animation.js
const text = `>> [SYSTEM_INITIALIZED]::::[MENU_OPTIONS_DETECTED]
        	[+] [DWELLER_MANAGEMENT]
        	[+] [INVENTORY_SYSTEMS]
        	[+] [RADIO_FREQUENCIES]
			[+] [TICKET_SYSTEM]
        	[+] [RETURN]
			[+] [LOG-OUT]
        	>> [SELECT_OPTION >> 0-5 <<]: `;

const element = document.getElementById("typing-text");
let i = 0;
let typingComplete = false;
const cursorChar = '<span style="border-left: 10px solid #cf9436; margin-left: 2px;"></span>'; // Cursor character
let cursorVisible = true;
let lineDelay = 2; // Extra delay after line breaks (ms)
let charDelay = 10;  // Delay between characters (ms)

// Define which lines should be links and their URLs
const linkLines = {
    "[DWELLER_MANAGEMENT]": CONTEXT_URLS.dwellers,
    "[INVENTORY_SYSTEMS]": CONTEXT_URLS.inventory,
    "[RADIO_FREQUENCIES]": CONTEXT_URLS.radio,
	"[TICKET_SYSTEM]": CONTEXT_URLS.tickets,
    "[RETURN]": CONTEXT_URLS.home,
	"[LOG-OUT]": CONTEXT_URLS.logout
};

function processTextWithLinks(displayedText, includeCursor = true) {
    // Split the text into lines
    let lines = displayedText.split('\n');
    
    // Process each line
    for (let j = 0; j < lines.length; j++) {
        const line = lines[j].trim();
        
        // Check if this line matches any of our link patterns
        for (const [linkText, linkUrl] of Object.entries(linkLines)) {
            if (line.includes(linkText)) {
                // Replace the text with a link
                lines[j] = lines[j].replace(linkText, 
                    `<a href="${linkUrl}" class="pipboy-link">${linkText}</a>`);
                break;
            }
        }
    }
    
    // Join the lines back together
    let result = lines.join('\n').replace(/\n/g, '<br>');
    if (includeCursor && cursorVisible) {
        result += cursorChar;
    }
    return result;
}

function typeWriter() {
    if (i < text.length) {
        const currentText = text.substring(0, i);
        element.innerHTML = processTextWithLinks(currentText);
        i++;
        setTimeout(typeWriter, charDelay);
    } else {
        // Final render with all links and no cursor
        typingComplete = true;
        element.innerHTML = processTextWithLinks(text, false);
    }
}


// Initialize
window.onload = function() {

    const style = document.createElement('style');
    style.textContent = `
		.pipboy-link {
		    color: #cf9436;
		    text-decoration: none;
		    padding: 0 5px;
		}
	
		.pipboy-link:hover {
		    color: #1a3f1a;
		    background-color: #cf9436;
		    border-bottom: 1px solid #1a3f1a;
		}
        .terminal-cursor {
            border-left: 10px solid #cf9436;
            margin-left: 2px;
            animation: blink 1s step-end infinite;
        }
        @keyframes blink {
            from, to { opacity: 1 }
            50% { opacity: 0 }
        }
    `;
    document.head.appendChild(style);
  // Start typing
  typeWriter();
  
  // Cursor blink effect
  setInterval(() => {
        if (!typingComplete) {
            cursorVisible = !cursorVisible;
            const currentText = text.substring(0, i);
            element.innerHTML = processTextWithLinks(currentText);
        }
		else if (typingComplete) {
		            cursorVisible = !cursorVisible;
		            const currentText = text.substring(0, i);
		            element.innerHTML = processTextWithLinks(currentText);
		        }
    }, 850);
};