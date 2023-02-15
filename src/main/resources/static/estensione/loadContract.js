
async function loadContract() {
    const [tab] = await chrome.tabs.query({ active: true, currentWindow: true });

    await chrome.scripting.executeScript({
        world: 'MAIN',
        target: {tabId: tab.id},
        files: ['web3.min.js', 'contract.js', 'showData.js']
    })
}
loadContract()