<script setup lang="ts">
import {ref} from "vue"
import {Item} from "~/scripts/items"
import { createItem } from '~/scripts/createItem'

// Initialize item with default values
const item = ref<Item>({
  id: 0,
  name: "",
  description: "",
  itemType: "",
  category: { name: "" },
  link: "",
  logoLink: ""
})

const handleSubmit = async (e: Event) => {
  e.preventDefault()
  await createItem(item.value)
  alert('Item created successfully!')
}

onMounted(() => {
  document.title = 'Beauver - Create Item'
})
</script>

<template>
  <div class="create-item-container">
    <h1>Create a New Item</h1>
    <form class="create-item-form" @submit="handleSubmit">
      <div class="form-group">
        <label for="itemName">Item Name:</label>
        <input type="text" id="itemName" v-model="item.name" required />
      </div>
      <div class="form-group">
        <label for="itemDescription">Description:</label>
        <input type="text" id="itemDescription" v-model="item.description" required />
      </div>
      <div class="form-group">
        <label for="itemType">Type:</label>
        <input type="text" id="itemType" v-model="item.itemType" required />
      </div>
      <div class="form-group">
        <label for="itemCategory">Category Name:</label>
        <input type="text" id="itemCategory" v-model="item.category.name" required />
      </div>
      <div class="form-group">
        <label for="itemLink">Link:</label>
        <input type="url" id="itemLink" v-model="item.link" required />
      </div>
      <div class="form-group">
        <label for="itemLogoLink">Logo Link:</label>
        <input type="url" id="itemLogoLink" v-model="item.logoLink" required />
      </div>
      <button type="submit">Create Item</button>
    </form>
  </div>
</template>