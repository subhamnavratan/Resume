const baseURL = "https://Resume-17.onrender.com"; // Replace with your backend URL
const resumeId = "64fa1111b9123e4f89d67890"; // Example resume ID
const mainContent = document.getElementById("mainContent");

function clearMain() {
    mainContent.innerHTML = "";
}

async function showSection(section) {
    clearMain();
    let url = `${baseURL}/${resumeId}`;

    try {
        const res = await fetch(url);
        if (!res.ok) throw new Error("Resume not found");
        const data = await res.json();

        switch(section) {
            case "about":
                mainContent.innerHTML = `
                    <h2>${data.name}</h2>
                    <p>${data.about}</p>
                `;
                break;

            case "contact":
                const contact = data.contactDetail;
                mainContent.innerHTML = `
                    <p>Email: ${contact.email}</p>
                    <p>Phone: ${contact.phoneNumber}</p>
                    <p>LinkedIn: <a href="${contact.linkedin}" target="_blank">${contact.linkedin}</a></p>
                    <p>GitHub: <a href="${contact.github}" target="_blank">${contact.github}</a></p>
                    <p>Address: ${contact.address}</p>
                `;
                break;

            case "education":
                const table = document.createElement("table");
                const thead = document.createElement("thead");
                const headerRow = document.createElement("tr");
                ["Level", "Institution", "Year", "Grade"].forEach(text => {
                    const th = document.createElement("th");
                    th.innerText = text;
                    headerRow.appendChild(th);
                });
                thead.appendChild(headerRow);
                table.appendChild(thead);

                const tbody = document.createElement("tbody");
                data.educationDetails.forEach(edu => {
                    const row = document.createElement("tr");
                    [edu.level, edu.institution, edu.year, edu.grade].forEach(text => {
                        const td = document.createElement("td");
                        td.innerText = text;
                        row.appendChild(td);
                    });
                    tbody.appendChild(row);
                });
                table.appendChild(tbody);
                mainContent.appendChild(table);
                break;

            case "skills":
                const skillsList = document.createElement("ul");
                data.skills.forEach(skill => {
                    const li = document.createElement("li");
                    li.innerText = skill;
                    skillsList.appendChild(li);
                });
                mainContent.appendChild(skillsList);
                break;

            case "projects":
                data.projects.forEach(project => {
                    const div = document.createElement("div");
                    div.innerHTML = `<h3>${project.title}</h3>
                                     <p>${project.description}</p>
                                     <a href="${project.link}" target="_blank">${project.link}</a>`;
                    mainContent.appendChild(div);
                });
                break;

            case "achievement":
                const achievementDiv = document.createElement("div");
                achievementDiv.innerHTML = `<p>${data.achievement}</p>`;

                // Add LeetCode and GFG links if they exist
                if (data.leetcodeProfile) {
                    const leetLink = document.createElement("p");
                    leetLink.innerHTML = `LeetCode: <a href="${data.leetcodeProfile}" target="_blank">${data.leetcodeProfile}</a>`;
                    achievementDiv.appendChild(leetLink);
                }

                if (data.gfgProfile) {
                    const gfgLink = document.createElement("p");
                    gfgLink.innerHTML = `GeeksforGeeks: <a href="${data.gfgProfile}" target="_blank">${data.gfgProfile}</a>`;
                    achievementDiv.appendChild(gfgLink);
                }

                mainContent.appendChild(achievementDiv);
                break;

            default:
                mainContent.innerHTML = `<p>Section not found</p>`;
        }
    } catch(err) {
        mainContent.innerHTML = `<p>Error: ${err.message}</p>`;
    }
}
;
