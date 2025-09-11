import { Item } from '~/scripts/items'

export async function createItem(item: Item) {
    const apiUrl = import.meta.env.VITE_API_URL
    try {
        const response = await fetch(`${apiUrl}/item/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item)
        })
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`)
        }
        const result = await response.json()
        console.log('Item created:', result)
        return result
    } catch (error) {
        console.error('Error creating item:', error)
        return null
    }
}