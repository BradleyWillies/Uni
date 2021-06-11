<?php

namespace App\Http\Controllers;

use App\Http\Requests\ContactFormRequest;
use App\Mail\ContactMail;
use App\Models\Event;
use App\Models\EventCategory;
use App\Models\Organiser;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Mail;

class StudentController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View|\Illuminate\Http\Response
     */
    public function index(Request $request)
    {
        $eventQuery = Event::query();

        if ($request->get('event_category_id')) {
            $eventQuery->where('event_category_id', $request->get('event_category_id'));
        }
        if ($request->get('event_heading')) {
            $sortOrder = $request->get('sort') ?? 'DESC';
            $eventQuery->orderBy($request->get('event_heading'), $sortOrder);
        }

        $events = $eventQuery->get();
        $eventCategories = EventCategory::all();
        return view('index', compact('events', 'eventCategories'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View|\Illuminate\Http\Response
     */
    public function show(Request $request, $id)
    {
        $event = Event::with('images')->where('id', $id)->first();
        $eventCategory = EventCategory::where('id', $event->event_category_id)->first();
        $organiser = Organiser::where('id', $event->organiser_id)->first();
        $organiserEmail = (User::where('id', $organiser->user_id)->first())->email;
        return view('event.show', compact('event', 'eventCategory', 'organiser', 'organiserEmail'));
    }

    /**
     * Adds 1 to the interest of the selected event.
     *
     * @param Request $request
     * @param int $id
     * @return \Illuminate\Http\RedirectResponse
     */
    public function addInterest(Request $request, $id)
    {
        $event = Event::find($id);
        $event->increment('interest_ranking');
        session(['addedInterest' . $id . Auth::id() => 'true']);
        return redirect()->route('student.event.show', $id);
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
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

    /**
     * Return the contact view for contacting an event organiser.
     *
     * @param $id
     * @return \Illuminate\Contracts\Foundation\Application|\Illuminate\Contracts\View\Factory|\Illuminate\Contracts\View\View
     */
    public function showContact($id)
    {
        $event = Event::find($id);
        return view('event.contact', compact('event'));
    }

    public function sendContactMail(ContactFormRequest $request, $id)
    {
        $event = Event::with('organiser')->where('id', $id)->first();
        $user = User::where('id', $event->organiser->user_id)->first();
        $eventOrganiserEmail = $user->email;

        Mail::to('bradley.willies@gmail.com')->send(new ContactMail($request->get('name'), $request->get('message'),  $request->get('email'), $event->name));
        return redirect()->route('student.event.contact', $id);
    }
}
