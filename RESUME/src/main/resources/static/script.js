// ============================
// CONFIG
// ============================
const baseURL = "https://Resume-23.onrender.com/resume"; // backend endpoint
const resumeName = "Alice Kumar"; // replace with actual resume name

// Select main content container (by class, not id)
const mainContent = document.querySelector(".content");

// ============================
// HELPERS
// ============================

// Clear content area
function clearMain() {
    if (mainContent) {
        mainContent.innerHTML = "";
    } else {
        console.error("❌ mainContent container not found in DOM!");
    }
}

// Fetch resume data once and cache
async function fetchResume() {
    const url = `${baseURL}/${encodeURIComponent(resumeName)}`;
    try {
        const res = await fetch(url);
        if (!res.ok) throw new Error(`Failed to fetch: ${res.status}`);
        return await res.json();
    } catch (err) {
        console.error("❌ Fetch error:", err);
        clearMain();
        mainContent.innerHTML = `<p style="color:red;">Error: ${err.message}</p>`;
        return null;
    }
}

// ============================
// SECTION RENDERERS
// ============================

function renderAbout(data) {
    mainContent.innerHTML = `
        <h2>${data.name || "No Name Available"}</h2>
        <p>${data.about || "No about information available."}</p>
    `;
}

function renderContact(contact) {
    mainContent.innerHTML = `
        <p>Email: ${contact.email || "N/A"}</p>
        <p>Phone: ${contact.phoneNumber || "N/A"}</p>
        <p>LinkedIn: ${contact.linkedin ? `<a href="${contact.linkedin}" target="_blank">${contact.linkedin}</a>` : "N/A"}</p>
        <p>GitHub: ${contact.github ? `<a href="${contact.github}" target="_blank">${contact.github}</a>` : "N/A"}</p>
        <p>Address: ${contact.address || "N/A"}</p>
    `;
}

function renderEducation(education) {
    if (!education || education.length === 0) {
        mainContent.innerHTML = "<p>No education details available.</p>";
        return;
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
    education.forEach(edu => {
        const row = document.createElement("tr");
        [edu.level, edu.institution, edu.year, edu.grade].forEach(val => {
            const td = document.createElement("td");
            td.innerText = val || "N/A";
            row.appendChild(td);
        });
        tbody.appendChild(row);
    });
    table.appendChild(tbody);
    mainContent.appendChild(table);
}

function renderSkills(skills) {
    if (!skills || skills.length === 0) {
        mainContent.innerHTML = "<p>No skills available.</p>";
        return;
    }
    const list = document.createElement("ul");
    skills.forEach(skill => {
        const li = document.createElement("li");
        li.innerText = skill;
        list.appendChild(li);
    });
    mainContent.appendChild(list);
}

function renderProjects(projects) {
    if (!projects || projects.length === 0) {
        mainContent.innerHTML = "<p>No projects available.</p>";
        return;
    }
    projects.forEach(project => {
        const div = document.createElement("div");
        div.innerHTML = `
            <h3>${project.title || "Untitled Project"}</h3>
            <p>${project.description || "No description available."}</p>
            ${project.link ? `<a href="${project.link}" target="_blank">${project.link}</a>` : ""}
        `;
        mainContent.appendChild(div);
    });
}

function renderAchievements(data) {
    if (!data.achievement && !data.leetcodeProfile && !data.gfgProfile) {
        mainContent.innerHTML = "<p>No achievements available.</p>";
        return;
    }
    const div = document.createElement("div");
    if (data.achievement) div.innerHTML += `<p>${data.achievement}</p>`;
    if (data.leetcodeProfile) div.innerHTML += `<p>LeetCode: <a href="${data.leetcodeProfile}" target="_blank">${data.leetcodeProfile}</a></p>`;
    if (data.gfgProfile) div.innerHTML += `<p>GeeksforGeeks: <a href="${data.gfgProfile}" target="_blank">${data.gfgProfile}</a></p>`;
    mainContent.appendChild(div);
}

// ============================
// MAIN CONTROLLER
// ============================

async function showSection(section) {
    clearMain();
    const data = await fetchResume();
    if (!data) return;

    switch (section) {
        case "about": renderAbout(data); break;
        case "contact": renderContact(data.contactDetail || {}); break;
        case "education": renderEducation(data.educationDetails); break;
        case "skills": renderSkills(data.skills); break;
        case "projects": renderProjects(data.projects); break;
        case "achievement": renderAchievements(data); break;
        default: mainContent.innerHTML = "<p>Section not found</p>";
    }
}

// ============================
// INIT
// ============================

// Load "about" section by default when page loads
document.addEventListener("DOMContentLoaded", () => {
    showSection("about");
});



