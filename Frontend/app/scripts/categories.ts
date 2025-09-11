import { ref } from 'vue'

export const categories = ref<string[]>([])

// Function to fetch categories that should be called in onMounted
export async function fetchCategories() {
    const apiUrl = import.meta.env.VITE_API_URL
    try {
        const response = await fetch(`${apiUrl}/category/getAll`)
        const result = await response.json()
        const categoryNames = result.Data.map((c: { name: string }) => c.name)

        // Update the reactive ref
        categories.value = categoryNames
        return categoryNames
    } catch (error) {
        console.error('Error fetching categories:', error)
        return []
    }
}