<script>
import layout from '@/Shared/Layout.vue'
export default { layout }
</script>

<script setup>

import { router } from '@inertiajs/vue3'
import pickBy from 'lodash/pickBy'
import throttle from 'lodash/throttle'

const props = defineProps({
  filters: Object,
  organizations: Object
})

const form = reactive({
  search: props.filters.search,
  trashed: props.filters.trashed
})

watch(
    form,
    throttle(function () {
      router.get('/organizations', pickBy(form), { preserveState: true })
    }, 150),
    {deep: true}
)

const reset = () => {
  for (let key in form) {
    form[key] = null
  }
}

</script>

<template>
  <inertia-head title="Organizations" />
  <div>
    <h1 class="mb-8 font-bold text-3xl">Organizations</h1>
    <div class="mb-6 flex justify-between items-center">
      <search-filter v-model="form.search" class="w-full max-w-md mr-4" @reset="reset">
        <label class="block text-slate-700">Trashed:</label>
        <select v-model="form.trashed" class="mt-1 w-full form-select">
          <option :value="null" />
          <option value="with">With Trashed</option>
          <option value="only">Only Trashed</option>
        </select>
      </search-filter>
      <inertia-link class="btn-indigo" href="/organizations/create">
        <span>Create</span>
        <span class="hidden md:inline"> Organization</span>
      </inertia-link>
    </div>
    <div class="bg-white rounded-md shadow overflow-x-auto">
      <table class="w-full whitespace-nowrap">
        <tr class="text-left font-bold">
          <th class="px-6 pt-6 pb-4">Name</th>
          <th class="px-6 pt-6 pb-4">City</th>
          <th class="px-6 pt-6 pb-4" colspan="2">Phone</th>
        </tr>
        <tr v-for="organization in organizations.data" :key="organization.id" class="hover:bg-slate-100 focus-within:bg-slate-100">
          <td class="border-t">
            <inertia-link class="px-6 py-4 flex items-center focus:text-indigo-500" :href="`/organizations/${organization.id}/edit`">
              {{ organization.name }}
              <icon v-if="organization.deleted" name="trash" class="shrink-0 w-3 h-3 fill-slate-400 ml-2" />
            </inertia-link>
          </td>
          <td class="border-t">
            <inertia-link class="px-6 py-4 flex items-center" :href="`/organizations/${organization.id}/edit`" tabindex="-1">
              {{ organization.city }}
            </inertia-link>
          </td>
          <td class="border-t">
            <inertia-link class="px-6 py-4 flex items-center" :href="`/organizations/${organization.id}/edit`" tabindex="-1">
              {{ organization.phone }}
            </inertia-link>
          </td>
          <td class="border-t w-px">
            <inertia-link class="px-4 flex items-center" :href="`/organizations/${organization.id}/edit`" tabindex="-1">
              <icon name="cheveron-right" class="block w-6 h-6 fill-slate-400" />
            </inertia-link>
          </td>
        </tr>
        <tr v-if="organizations.data.length === 0">
          <td class="border-t px-6 py-4" colspan="4">No organizations found.</td>
        </tr>
      </table>
    </div>
    <pagination class="mt-6" :links="organizations.links" />
  </div>
</template>