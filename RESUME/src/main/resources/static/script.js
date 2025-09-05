// ============================
// CONFIG
// ============================
const baseURL = "https://Resume-25.onrender.com/resume"; // backend endpoint
const resumeName = "Subham Kumar Navratan"; // replace with actual resume name

// Select main content container
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
        const errorMsg = document.createElement("p");
        errorMsg.style.color = "red";
        errorMsg.innerText = `Error: ${err.message}`;
        mainContent.appendChild(errorMsg);
        return null;
    }
}

// ============================
// SECTION RENDERERS
// ============================

function renderAbout(data) {
    const div = document.createElement("div");
    const h2 = document.createElement("h2");
    h2.innerText = data.name || "No Name Available";
    const p = document.createElement("p");
    p.innerText = data.about || "No about information available.";
    div.appendChild(h2);
    div.appendChild(p);
    mainContent.appendChild(div);
}

function renderContact(contact) {
    const div = document.createElement("div");

    const email = document.createElement("p");
    email.innerText = `Email: ${contact.email || "N/A"}`;
    const phone = document.createElement("p");
    phone.innerText = `Phone: ${contact.phoneNumber || "N/A"}`;
    const linkedin = document.createElement("p");
    linkedin.innerHTML = `LinkedIn: ${contact.linkedin ? `<a href="${contact.linkedin}" target="_blank">${contact.linkedin}</a>` : "N/A"}`;
    const github = document.createElement("p");
    github.innerHTML = `GitHub: ${contact.github ? `<a href="${contact.github}" target="_blank">${contact.github}</a>` : "N/A"}`;
    const address = document.createElement("p");
    address.innerText = `Address: ${contact.address || "N/A"}`;

    div.append(email, phone, linkedin, github, address);
    mainContent.appendChild(div);
}

function renderEducation(education) {
    if (!education || education.length === 0) {
        const p = document.createElement("p");
        p.innerText = "No education details available.";
        mainContent.appendChild(p);
        return;
    }

    const table = document.createElement("table");
    table.border = "1";
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
        const p = document.createElement("p");
        p.innerText = "No skills available.";
        mainContent.appendChild(p);
        return;
    }
    const ul = document.createElement("ul");
    skills.forEach(skill => {
        const li = document.createElement("li");
        li.innerText = skill;
        ul.appendChild(li);
    });
    mainContent.appendChild(ul);
}

function renderProjects(projects) {
    if (!projects || projects.length === 0) {
        const p = document.createElement("p");
        p.innerText = "No projects available.";
        mainContent.appendChild(p);
        return;
    }

    projects.forEach(project => {
        const div = document.createElement("div");
        const h3 = document.createElement("h3");
        h3.innerText = project.title || "Untitled Project";
        const p = document.createElement("p");
        p.innerText = project.description || "No description available.";
        div.appendChild(h3);
        div.appendChild(p);

        if (project.link) {
            const a = document.createElement("a");
            a.href = project.link;
            a.target = "_blank";
            a.innerText = project.link;
            div.appendChild(a);
        }

        mainContent.appendChild(div);
    });
}

function renderAchievements(data) {
    if (!data.achievement && !data.leetcodeProfile && !data.gfgProfile) {
        const p = document.createElement("p");
        p.innerText = "No achievements available.";
        mainContent.appendChild(p);
        return;
    }

    const div = document.createElement("div");
    if (data.achievement) {
        const p = document.createElement("p");
        p.innerText = data.achievement;
        div.appendChild(p);
    }
    if (data.leetcodeProfile) {
        const p = document.createElement("p");
        p.innerHTML = `LeetCode: <a href="${data.leetcodeProfile}" target="_blank">${data.leetcodeProfile}</a>`;
        div.appendChild(p);
    }
    if (data.gfgProfile) {
        const p = document.createElement("p");
        p.innerHTML = `GeeksforGeeks: <a href="${data.gfgProfile}" target="_blank">${data.gfgProfile}</a>`;
        div.appendChild(p);
    }

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
        default:
            const p = document.createElement("p");
            p.innerText = "Section not found";
            mainContent.appendChild(p);
    }
}

// ============================
// INIT
// ============================

// Load "about" section by default
document.addEventListener("DOMContentLoaded", () => {
    showSection("about");
});




