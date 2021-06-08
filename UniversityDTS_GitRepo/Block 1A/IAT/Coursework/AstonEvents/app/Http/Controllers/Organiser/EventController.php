<?php

namespace App\Http\Controllers\Organiser;

use App\Http\Controllers\Controller;
use App\Http\Requests\EventRequest;
use App\Models\Event;
use App\Models\EventCategory;
use App\Models\Image;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;

class EventController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function index()
    {
        $events = Event::where('organiser_id', '=', auth()->user()->organiser->id)->orderBy('date_time', 'DESC')->get();
        return view('organiser.dashboard', compact('events'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function create()
    {
        $eventCategories = EventCategory::all();
        return view('organiser.event.create', compact('eventCategories'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  EventRequest  $request
     * @return \Illuminate\Http\RedirectResponse
     */
    public function store(EventRequest $request)
    {
        $event = new Event();
        $event->fill($request->all());
        $event->organiser_id = auth()->user()->organiser->id;
        $event->save();
        $images = $request->file('images');
        foreach ($images as $image) {
            $path = 'event_images/' . $event->id;
            Storage::disk('public')->put($path, $image);
            $imageRecord = new Image();
            $imageRecord->event_id = $event->id;
            $imageRecord->file_path = $path . '/' . $image->hashName();
            $imageRecord->save();
        }
        return redirect()->route("dashboard.event.index")->with("success", "Event created");
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function show($id)
    {
        $event = Event::with('images')->where('id', $id)->first();
        $eventCategories = EventCategory::all();
        return view('organiser.event.show', compact('event', 'eventCategories'));
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  EventRequest $request
     * @param int $id
     * @return \Illuminate\Http\RedirectResponse
     */
    public function edit(EventRequest $request, int $id)
    {
        $event = Event::find($id);
        $event->update($request->all());
        $images = $request->file('images');
        $path = 'event_images/' . $event->id;
        Storage::disk('public')->deleteDirectory($path);
        $event->images()->delete();
        foreach ($images as $image) {
            Storage::disk('public')->put($path, $image);
            $imageRecord = new Image();
            $imageRecord->event_id = $event->id;
            $imageRecord->file_path = $path . '/' . $image->hashName();
            $imageRecord->save();
        }
        return redirect()->route("dashboard.event.index")->with("success", "Event updated");
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
    }
}
