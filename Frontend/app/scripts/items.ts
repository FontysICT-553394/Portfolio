import { ref } from 'vue'

// Define Item class
export class Item {
    id: number
    name: string
    description: string
    itemType: string
    logoLink: string
    link: string
    category: { name: string }

    constructor(itemData: any) {
        this.id = itemData.id
        this.name = itemData.name
        this.description = itemData.description
        this.itemType = itemData.itemType
        this.link = itemData.link
        this.logoLink = itemData.logoLink
        this.category = itemData.category
    }
}

// Export a reactive ref that components can use
export const itemsByCategory = ref<Record<string, Item[]>>({})

// Function to fetch items grouped by category
export async function fetchItemsByCategories() {
    const apiUrl = import.meta.env.VITE_API_URL
    try {
        const response = await fetch(`${apiUrl}/item/getAll`)
        const result = await response.json()

        const grouped = result.Data.reduce((acc: Record<string, Item[]>, item: any) => {
            const categoryName = item.category.name
            if (!acc[categoryName]) acc[categoryName] = []
            acc[categoryName].push(new Item(item))
            return acc
        }, {})

        // Update the reactive ref
        itemsByCategory.value = grouped
        return grouped
    } catch (error) {
        console.error('Error fetching items:', error)
        return {}
    }
}