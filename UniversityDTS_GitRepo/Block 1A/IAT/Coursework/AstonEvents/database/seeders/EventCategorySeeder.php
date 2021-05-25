<?php

namespace Database\Seeders;

use App\Models\EventCategory;
use Illuminate\Database\Seeder;

class EventCategorySeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        echo "Generating event categories...\n";
        EventCategory::create([
           'name' => 'Sport'
        ]);

        EventCategory::create([
            'name' => 'Culture'
        ]);

        EventCategory::create([
            'name' => 'Other'
        ]);
    }
}
