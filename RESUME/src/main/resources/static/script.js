const baseURL = "https://Resume-22.onrender.com/resume"; // backend endpoint
const name = "Alice Kumar"; // replace with actual resume name
const mainContent = document.getElementById("mainContent");

function clearMain() {
    mainContent.innerHTML = "";
}

async function showSection(section) {
    clearMain();
    let url = `${baseURL}/${encodeURIComponent(name)}`;

    try {
        const res = await fetch(url);
        if (!res.ok) throw new Error("Resume not found");
        const data = await res.json();

        console.log("Resume Data:", data); // Debug log

        switch(section) {
            case "about":
                mainContent.innerHTML = `
                    <h2>${data.name || "No Name Available"}</h2>
                    <p>${data.about || "No about information available."}</p>
                `;
                break;

            case "contact":
                const contact = data.contactDetail || {};
                mainContent.innerHTML = `
                    <p>Email: ${contact.email || "N/A"}</p>
                    <p>Phone: ${contact.phoneNumber || "N/A"}</p>
                    <p>LinkedIn: ${contact.linkedin ? `<a href="${contact.linkedin}" target="_blank">${contact.linkedin}</a>` : "N/A"}</p>
                    <p>GitHub: ${contact.github ? `<a href="${contact.github}" target="_blank">${contact.github}</a>` : "N/A"}</p>
                    <p>Address: ${contact.address || "N/A"}</p>
                `;
                break;

            case "education":
                if (data.educationDetails && data.educationDetails.length > 0) {
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
                        [edu.level || "N/A", edu.institution || "N/A", edu.year || "N/A", edu.grade || "N/A"].forEach(text => {
                            const td = document.createElement("td");
                            td.innerText = text;
                            row.appendChild(td);
                        });
                        tbody.appendChild(row);
                    });
                    table.appendChild(tbody);
                    mainContent.appendChild(table);
                } else {
                    mainContent.innerHTML = "<p>No education details available.</p>";
                }
                break;

            case "skills":
                if (data.skills && data.skills.length > 0) {
                    const skillsList = document.createElement("ul");
                    data.skills.forEach(skill => {
                        const li = document.createElement("li");
                        li.innerText = skill;
                        skillsList.appendChild(li);
                    });
                    mainContent.appendChild(skillsList);
                } else {
                    mainContent.innerHTML = "<p>No skills available.</p>";
                }
                break;

            case "projects":
                if (data.projects && data.projects.length > 0) {
                    data.projects.forEach(project => {
                        const div = document.createElement("div");
                        div.innerHTML = `
                            <h3>${project.title || "Untitled Project"}</h3>
                            <p>${project.description || "No description available."}</p>
                            ${project.link ? `<a href="${project.link}" target="_blank">${project.link}</a>` : ""}
                        `;
                        mainContent.appendChild(div);
                    });
                } else {
                    mainContent.innerHTML = "<p>No projects available.</p>";
                }
                break;

            case "achievement":
                if (data.achievement || data.leetcodeProfile || data.gfgProfile) {
                    const achievementDiv = document.createElement("div");
                    if (data.achievement) {
                        achievementDiv.innerHTML += `<p>${data.achievement}</p>`;
                    }
                    if (data.leetcodeProfile) {
                        achievementDiv.innerHTML += `<p>LeetCode: <a href="${data.leetcodeProfile}" target="_blank">${data.leetcodeProfile}</a></p>`;
                    }
                    if (data.gfgProfile) {
                        achievementDiv.innerHTML += `<p>GeeksforGeeks: <a href="${data.gfgProfile}" target="_blank">${data.gfgProfile}</a></p>`;
                    }
                    mainContent.appendChild(achievementDiv);
                } else {
                    mainContent.innerHTML = "<p>No achievements available.</p>";
                }
                break;

            default:
                mainContent.innerHTML = "<p>Section not found</p>";
        }
    } catch(err) {
        mainContent.innerHTML = `<p>Error: ${err.message}</p>`;
    }
}


