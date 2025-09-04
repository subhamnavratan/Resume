const baseURL = "https://Resume-21.onrender.com/resume"; // backend endpoint
const name = "Alice Kumar"; // replace with actual resume name
const mainContent = document.getElementById("mainContent");

function clearMain() {
    mainContent.innerHTML = "";
}

async function showSection(section) {
    clearMain();
    let url = `${baseURL}/${encodeURIComponent(name)}`; // fetch by name now

    try {
        const res = await fetch(url);
        if (!res.ok) throw new Error("Resume not found");
        const data = await res.json();

        switch (section) {
            case "about":
                mainContent.innerHTML = `
                    <h2>${data.name || "Unknown Name"}</h2>
                    <p>${data.about || "No about section available."}</p>
                `;
                break;

            case "contact":
                const contact = data.contactDetail;
                if (!contact) {
                    mainContent.innerHTML = `<p>No contact details available.</p>`;
                    break;
                }
                mainContent.innerHTML = `
                    <p>Email: ${contact.email || "N/A"}</p>
                    <p>Phone: ${contact.phoneNumber || "N/A"}</p>
                    <p>LinkedIn: ${contact.linkedin ? `<a href="${contact.linkedin}" target="_blank">${contact.linkedin}</a>` : "N/A"}</p>
                    <p>GitHub: ${contact.github ? `<a href="${contact.github}" target="_blank">${contact.github}</a>` : "N/A"}</p>
                    <p>Address: ${contact.address || "N/A"}</p>
                `;
                break;

            case "education":
                if (!data.educationDetails || data.educationDetails.length === 0) {
                    mainContent.innerHTML = `<p>No education details available.</p>`;
                    break;
                }
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
                        td.innerText = text || "N/A";
                        row.appendChild(td);
                    });
                    tbody.appendChild(row);
                });
                table.appendChild(tbody);
                mainContent.appendChild(table);
                break;

            case "skills":
                if (!data.skills || data.skills.length === 0) {
                    mainContent.innerHTML = `<p>No skills available.</p>`;
                    break;
                }
                const skillsList = document.createElement("ul");
                data.skills.forEach(skill => {
                    const li = document.createElement("li");
                    li.innerText = skill || "N/A";
                    skillsList.appendChild(li);
                });
                mainContent.appendChild(skillsList);
                break;

            case "projects":
                if (!data.projects || data.projects.length === 0) {
                    mainContent.innerHTML = `<p>No projects available.</p>`;
                    break;
                }
                data.projects.forEach(project => {
                    const div = document.createElement("div");
                    div.innerHTML = `
                        <h3>${project.title || "Untitled Project"}</h3>
                        <p>${project.description || "No description provided."}</p>
                        ${project.link ? `<a href="${project.link}" target="_blank">${project.link}</a>` : ""}
                    `;
                    mainContent.appendChild(div);
                });
                break;

            case "achievement":
                if (!data.achievement && !data.leetcodeProfile && !data.gfgProfile) {
                    mainContent.innerHTML = `<p>No achievements available.</p>`;
                    break;
                }
                const achievementDiv = document.createElement("div");
                achievementDiv.innerHTML = `<p>${data.achievement || ""}</p>`;

                if (data.leetcodeProfile) {
                    achievementDiv.innerHTML += `<p>LeetCode: <a href="${data.leetcodeProfile}" target="_blank">${data.leetcodeProfile}</a></p>`;
                }

                if (data.gfgProfile) {
                    achievementDiv.innerHTML += `<p>GeeksforGeeks: <a href="${data.gfgProfile}" target="_blank">${data.gfgProfile}</a></p>`;
                }

                mainContent.appendChild(achievementDiv);
                break;

            default:
                mainContent.innerHTML = `<p>Section not found</p>`;
        }
    } catch (err) {
        mainContent.innerHTML = `<p>Error: ${err.message}</p>`;
    }
}

