<script setup lang="ts">
import { onMounted } from 'vue'
import { categories, fetchCategories } from '~/scripts/categories'
import { itemsByCategory, fetchItemsByCategories } from '~/scripts/items'
import '../css/homepage.css'

onMounted(async () => {
  try {
    await fetchCategories()
    await fetchItemsByCategories()
  } catch (error) {
    console.error('Failed to fetch data:', error)
  }
})
</script>

<template>
  <head>
    <title>Beauver - Portfolio</title>
    <meta name="description" content="A list of categories and their items." />
    <link rel="icon" href="/favicon.ico" />
  </head>

  <div class="categories-container">
    <div class="categories-list">
      <div v-for="category in categories" :key="category" class="category-block">
        <h2>{{ category }}</h2>
        <ul>
          <ul class="items-list" style="display: flex; gap: 1em; list-style: none; padding: 0;">
            <li v-for="item in itemsByCategory[category]" :key="item.id">
              <img :src="item.logoLink" :title="item.name" alt="Logo" style="height: 2em; vertical-align: middle;" />
            </li>
          </ul>
        </ul>
      </div>
    </div>
  </div>
</template>